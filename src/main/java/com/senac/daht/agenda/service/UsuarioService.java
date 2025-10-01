package com.senac.daht.agenda.service;

import com.senac.daht.agenda.dto.request.UsuarioDTORequest;
import com.senac.daht.agenda.dto.response.UsuarioDTOResponse;
import com.senac.daht.agenda.entity.Usuario;
import com.senac.daht.agenda.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // --- Métodos de Conversão ---
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
        usuario.setSenha(request.getSenha());
        usuario.setStatus(request.getStatus());
        return usuario;
    }

    // --- Métodos CRUD com Apagado Lógico ---

    @Transactional
    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest request) {
        Usuario usuario = convertToEntity(request);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDto(savedUsuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTOResponse> listarUsuarios() {
        // Usando o listarAtivos() customizado
        return usuarioRepository.listarAtivos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTOResponse listarPorId(Integer id) {
        // Usando o findById() customizado com filtro de status
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário ativo com ID " + id + " não encontrado."));
        return convertToDto(usuario);
    }

    @Transactional
    public UsuarioDTOResponse atualizarUsuario(Integer id, UsuarioDTORequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário ativo com ID " + id + " não encontrado para atualização."));

        // Aplica as atualizações do DTO
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setDataNascimento(request.getDataNascimento());
        if (request.getSenha() != null && !request.getSenha().isEmpty()) {
            usuario.setSenha(request.getSenha());
        }
        usuario.setStatus(request.getStatus());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return convertToDto(updatedUsuario);
    }

    @Transactional
    public void deletarUsuario(Integer id) {
        // Verifica a existência do registro ativo
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário ativo com ID " + id + " não encontrado para deleção lógica.");
        }
        // Usa o método apagarLogico()
        usuarioRepository.apagarLogico(id);
    }
}