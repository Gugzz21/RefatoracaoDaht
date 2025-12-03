package com.senac.daht.agenda.service;

import com.senac.daht.agenda.config.SecurityConfiguration;
import com.senac.daht.agenda.dto.CreateUserDto;
import com.senac.daht.agenda.dto.LoginUserDto;
import com.senac.daht.agenda.dto.RecoveryJwtTokenDto;
import com.senac.daht.agenda.dto.request.UsuarioDTORequest;
import com.senac.daht.agenda.dto.response.UsuarioDTOResponse;
import com.senac.daht.agenda.entity.Role;
import com.senac.daht.agenda.entity.RoleName;
import com.senac.daht.agenda.entity.Usuario;
import com.senac.daht.agenda.repository.RoleRepository;
import com.senac.daht.agenda.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository; // <--- NOVO: Injetando o repositório de roles

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Construtor atualizado
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, SecurityConfiguration securityConfiguration, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.securityConfiguration = securityConfiguration;
        this.roleRepository = roleRepository;
    }

    private UsuarioDTOResponse convertToDto(Usuario usuario) {
        UsuarioDTOResponse response = new UsuarioDTOResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setDataNascimento(usuario.getDataNascimento());
        response.setStatus(usuario.getStatus());

        if (usuario.getPersonagem() != null) {
            response.setPersonagemId(usuario.getPersonagem().getId());
        }
        return response;
    }

    private Usuario convertToEntity(UsuarioDTORequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setDataNascimento(request.getDataNascimento());

        String senhaHash = securityConfiguration.passwordEncoder().encode(request.getSenha());
        usuario.setSenha(senhaHash);

        usuario.setStatus(request.getStatus() != null ? request.getStatus() : 1); // Padrão 1 (ativo)

        return usuario;
    }

    @Transactional
    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        Usuario usuario = convertToEntity(request);

        // --- LÓGICA DE ROLE ADICIONADA ---
        // Busca a role "ROLE_USER" no banco para atribuir ao novo cadastro
        Role roleUser = roleRepository.findByNome(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Erro: Role ROLE_USER não encontrada no banco de dados."));

        usuario.setRoles(List.of(roleUser));
        // ---------------------------------

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDto(savedUsuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTOResponse> listarAtivos() {
        return usuarioRepository.listarAtivos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTOResponse listarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário ativo com ID " + id + " não encontrado."));
        return convertToDto(usuario);
    }

    @Transactional
    public UsuarioDTOResponse atualizarUsuario(Integer id, UsuarioDTORequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário ativo com ID " + id + " não encontrado para atualização."));
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setDataNascimento(request.getDataNascimento());
        if (request.getSenha() != null && !request.getSenha().isEmpty()) {
            String senhaHash = securityConfiguration.passwordEncoder().encode(request.getSenha());
            usuario.setSenha(senhaHash);
        }

        usuario.setStatus(request.getStatus());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return convertToDto(updatedUsuario);
    }

    @Transactional
    public void deletarUsuario(Integer id) {
        if (!usuarioRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Usuário ativo com ID " + id + " não encontrado para deleção lógica.");
        }
        usuarioRepository.apagarLogico(id);
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDto createUserDto) {
        Role role = new Role();
        role.setNome(createUserDto.role());

        Usuario newUser = new Usuario();
        newUser.setEmail(createUserDto.email());
        newUser.setSenha(securityConfiguration.passwordEncoder().encode(createUserDto.password()));
        newUser.setRoles(List.of(role));

        usuarioRepository.save(newUser);
    }
}