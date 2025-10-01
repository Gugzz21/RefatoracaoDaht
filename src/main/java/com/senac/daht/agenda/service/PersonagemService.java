package com.senac.daht.agenda.service;

import com.senac.daht.agenda.dto.request.PersonagemDTORequest;
import com.senac.daht.agenda.dto.response.PersonagemDTOResponse;
import com.senac.daht.agenda.entity.Personagem;
import com.senac.daht.agenda.entity.Usuario;
import com.senac.daht.agenda.repository.PersonagemRepository;
import com.senac.daht.agenda.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PersonagemService(PersonagemRepository personagemRepository, UsuarioRepository usuarioRepository) {
        this.personagemRepository = personagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // --- Métodos de Conversão ---
    private PersonagemDTOResponse convertToDto(Personagem personagem) {
        PersonagemDTOResponse response = new PersonagemDTOResponse();
        response.setId(personagem.getId().longValue()); // Supondo que o DTO Response usa Long para o ID
        response.setNickname(personagem.getNickname());
        response.setVida(personagem.getVida());
        response.setOuro(personagem.getOuro());
        response.setXp(personagem.getXp());
        response.setNivel(personagem.getNivel());
        response.setStatus(personagem.getStatus());

        if (personagem.getUsuario() != null) {
            response.setUsuarioId(personagem.getUsuario().getId());
            response.setNomeUsuario(personagem.getUsuario().getNome());
        }
        return response;
    }

    private Personagem convertToEntity(PersonagemDTORequest request) {
        Personagem personagem = new Personagem();
        personagem.setNickname(request.getNickname());
        personagem.setVida(request.getVida());
        personagem.setOuro(request.getOuro());
        personagem.setXp(request.getXp());
        personagem.setNivel(request.getNivel());
        personagem.setStatus(request.getStatus());
        return personagem;
    }

    // --- Métodos CRUD com Apagado Lógico ---

    @Transactional
    public PersonagemDTOResponse criarPersonagem(PersonagemDTORequest request) {
        // Usando o findById filtrado por status
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário ativo com ID " + request.getUsuarioId() + " não encontrado."));

        Personagem personagem = convertToEntity(request);
        personagem.setUsuario(usuario);

        Personagem savedPersonagem = personagemRepository.save(personagem);
        return convertToDto(savedPersonagem);
    }

    @Transactional(readOnly = true)
    public List<PersonagemDTOResponse> listarPersonagens() {
        return personagemRepository.listarAtivos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonagemDTOResponse listarPorId(Integer id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + id + " não encontrado."));
        return convertToDto(personagem);
    }

    @Transactional
    public PersonagemDTOResponse atualizarPersonagem(Integer id, PersonagemDTORequest request) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + id + " não encontrado."));

        // Aplica as atualizações do DTO
        personagem.setNickname(request.getNickname());
        personagem.setVida(request.getVida());
        personagem.setOuro(request.getOuro());
        personagem.setXp(request.getXp());
        personagem.setNivel(request.getNivel());
        personagem.setStatus(request.getStatus());

        if (request.getUsuarioId() != null && !personagem.getUsuario().getId().equals(request.getUsuarioId())) {
            // Usando o findById filtrado por status
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário ativo com ID " + request.getUsuarioId() + " não encontrado."));
            personagem.setUsuario(usuario);
        }

        Personagem updatedPersonagem = personagemRepository.save(personagem);
        return convertToDto(updatedPersonagem);
    }

    @Transactional
    public void deletarPersonagem(Integer id) {
        // Não precisamos de existsById se findById já lança EntityNotFoundException
        if (!personagemRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Personagem ativo com ID " + id + " não encontrado para deleção lógica.");
        }
        personagemRepository.apagarLogico(id);
    }
}