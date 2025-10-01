package com.senac.daht.agenda.service;

import com.senac.daht.agenda.dto.request.GanhoDTORequest;
import com.senac.daht.agenda.dto.response.GanhoDTOResponse;
import com.senac.daht.agenda.entity.Ganho;
import com.senac.daht.agenda.entity.Personagem;
import com.senac.daht.agenda.repository.GanhoRepository;
import com.senac.daht.agenda.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GanhoService {

    private final GanhoRepository ganhoRepository;
    private final PersonagemRepository personagemRepository;

    @Autowired
    public GanhoService(GanhoRepository ganhoRepository, PersonagemRepository personagemRepository) {
        this.ganhoRepository = ganhoRepository;
        this.personagemRepository = personagemRepository;
    }


    private GanhoDTOResponse convertToDto(Ganho ganho) {
        GanhoDTOResponse response = new GanhoDTOResponse();
        response.setId(ganho.getId());
        response.setOuro(ganho.getOuro());
        response.setXp(ganho.getXp());
        response.setNivel(ganho.getNivel());
        response.setVida(ganho.getVida());

        if (ganho.getPersonagem() != null) {
            response.setPersonagemId(ganho.getPersonagem().getId());
        }
        return response;
    }

    private Ganho convertToEntity(GanhoDTORequest request) {
        Ganho ganho = new Ganho();
        ganho.setOuro(request.getOuro());
        ganho.setXp(request.getXp());
        ganho.setNivel(request.getNivel());
        ganho.setVida(request.getVida());

        return ganho;
    }



    @Transactional
    public GanhoDTOResponse criarGanho(GanhoDTORequest request) {

        Personagem personagem = personagemRepository.findById(request.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));

        Ganho ganho = convertToEntity(request);
        ganho.setPersonagem(personagem);

        Ganho savedGanho = ganhoRepository.save(ganho);
        return convertToDto(savedGanho);
    }

    @Transactional(readOnly = true)
    public List<GanhoDTOResponse> listarGanhos() {

        return ganhoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GanhoDTOResponse listarPorId(Integer id) {

        Ganho ganho = ganhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ganho com ID " + id + " não encontrado."));
        return convertToDto(ganho);
    }

    @Transactional
    public GanhoDTOResponse atualizarGanho(Integer id, GanhoDTORequest request) {

        Ganho ganho = ganhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ganho com ID " + id + " não encontrado para atualização."));


        if (!ganho.getPersonagem().getId().equals(request.getPersonagemId())) {
            Personagem novoPersonagem = personagemRepository.findById(request.getPersonagemId())
                    .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));
            ganho.setPersonagem(novoPersonagem);
        }


        ganho.setOuro(request.getOuro());
        ganho.setXp(request.getXp());
        ganho.setNivel(request.getNivel());
        ganho.setVida(request.getVida());


        Ganho updatedGanho = ganhoRepository.save(ganho);
        return convertToDto(updatedGanho);
    }

    @Transactional
    public void deletarGanho(Integer id) {

        if (!ganhoRepository.existsById(id)) {
            throw new EntityNotFoundException("Ganho com ID " + id + " não encontrado para deleção.");
        }

        ganhoRepository.deleteById(id);
    }
}