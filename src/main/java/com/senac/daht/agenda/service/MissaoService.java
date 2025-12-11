package com.senac.daht.agenda.service;

import com.senac.daht.agenda.dto.request.MissaoDTORequest;
import com.senac.daht.agenda.dto.response.MissaoDTOResponse;
import com.senac.daht.agenda.entity.Missao;
import com.senac.daht.agenda.entity.Personagem;
import com.senac.daht.agenda.repository.MissaoRepository;
import com.senac.daht.agenda.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final PersonagemRepository personagemRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository, PersonagemRepository personagemRepository) {
        this.missaoRepository = missaoRepository;
        this.personagemRepository = personagemRepository;
    }

    @Transactional
    public MissaoDTOResponse criarMissao(MissaoDTORequest request) {
        Personagem personagem = personagemRepository.findById(request.getPersonagemId().intValue())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));

        Missao missao = new Missao();
        missao.setDescricao(request.getDescricao());
        missao.setRepeticao(request.getRepeticao());
        missao.setDificuldade(request.getDificuldade());
        // Setting the effect from the request to the entity
        missao.setEfeito(request.getEfeito());
        missao.setDataFinalizacao(request.getDataFinalizacao());
        missao.setDataInicio(request.getDataInicio());
        missao.setStatus(request.getStatus());
        missao.setPersonagem(personagem);

        Missao savedMissao = missaoRepository.save(missao);

        // Converting to Response
        MissaoDTOResponse response = new MissaoDTOResponse();
        response.setId(savedMissao.getId());
        response.setDescricao(savedMissao.getDescricao());
        response.setRepeticao(savedMissao.getRepeticao());
        response.setDificuldade(savedMissao.getDificuldade());
        // IMPORTANT: Sending the effect back to the frontend
        response.setEfeito(savedMissao.getEfeito());
        response.setDataFinalizacao(savedMissao.getDataFinalizacao());
        response.setDataInicio(savedMissao.getDataInicio());
        response.setStatus(savedMissao.getStatus());
        if (savedMissao.getPersonagem() != null) {
            response.setPersonagemId(savedMissao.getPersonagem().getId().longValue());
        }

        return response;
    }

    @Transactional(readOnly = true)
    public List<MissaoDTOResponse> listarMissoes() {
        return missaoRepository.listarAtivos().stream()
                .map(missao -> {
                    MissaoDTOResponse response = new MissaoDTOResponse();
                    response.setId(missao.getId());
                    response.setDescricao(missao.getDescricao());
                    response.setRepeticao(missao.getRepeticao());
                    response.setDificuldade(missao.getDificuldade());
                    // IMPORTANT: Sending the effect back to the frontend
                    response.setEfeito(missao.getEfeito());
                    response.setDataFinalizacao(missao.getDataFinalizacao());
                    response.setDataInicio(missao.getDataInicio());
                    response.setStatus(missao.getStatus());
                    if (missao.getPersonagem() != null) {
                        response.setPersonagemId(missao.getPersonagem().getId().longValue());
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MissaoDTOResponse listarPorId(Integer id) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão ativa com ID " + id + " não encontrada."));
        MissaoDTOResponse response = new MissaoDTOResponse();
        response.setId(missao.getId());
        response.setDescricao(missao.getDescricao());
        response.setRepeticao(missao.getRepeticao());
        response.setDificuldade(missao.getDificuldade());
        // IMPORTANT: Sending the effect back to the frontend
        response.setEfeito(missao.getEfeito());
        response.setDataFinalizacao(missao.getDataFinalizacao());
        response.setDataInicio(missao.getDataInicio());
        response.setStatus(missao.getStatus());
        if (missao.getPersonagem() != null) {
            response.setPersonagemId(missao.getPersonagem().getId().longValue());
        }
        return response;
    }

    @Transactional
    public MissaoDTOResponse atualizarMissao(Integer id, MissaoDTORequest request) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Missão ativa com ID " + id + " não encontrada para atualização."));
        if (!missao.getPersonagem().getId().equals(request.getPersonagemId().intValue())) {
            Personagem novoPersonagem = personagemRepository.findById(request.getPersonagemId().intValue())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));
            missao.setPersonagem(novoPersonagem);
        }
        missao.setDescricao(request.getDescricao());
        missao.setRepeticao(request.getRepeticao());
        missao.setDificuldade(request.getDificuldade());
        // Updating the effect
        missao.setEfeito(request.getEfeito());
        missao.setDataFinalizacao(request.getDataFinalizacao());
        missao.setDataInicio(request.getDataInicio());
        missao.setStatus(request.getStatus());

        Missao updatedMissao = missaoRepository.save(missao);

        MissaoDTOResponse response = new MissaoDTOResponse();
        response.setId(updatedMissao.getId());
        response.setDescricao(updatedMissao.getDescricao());
        response.setRepeticao(updatedMissao.getRepeticao());
        response.setDificuldade(updatedMissao.getDificuldade());
        // IMPORTANT: Sending the effect back to the frontend
        response.setEfeito(updatedMissao.getEfeito());
        response.setDataFinalizacao(updatedMissao.getDataFinalizacao());
        response.setDataInicio(updatedMissao.getDataInicio());
        response.setStatus(updatedMissao.getStatus());
        if (updatedMissao.getPersonagem() != null) {
            response.setPersonagemId(updatedMissao.getPersonagem().getId().longValue());
        }
        return response;
    }

    @Transactional
    public void deletarMissao(Integer id) {
        if (!missaoRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Missão ativa com ID " + id + " não encontrada para deleção lógica.");
        }
        missaoRepository.apagarLogico(id);
    }
}