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

    // Método Privado: Converte Entity -> DTO Response
    private GanhoDTOResponse convertToDto(Ganho ganho) {
        GanhoDTOResponse response = new GanhoDTOResponse();
        response.setId(ganho.getId());
        response.setOuro(ganho.getOuro());
        response.setXp(ganho.getXp());
        response.setNivel(ganho.getNivel());
        response.setVida(ganho.getVida());
        response.setStatus(ganho.getStatus());
        if (ganho.getPersonagem() != null) {
            response.setPersonagemId(ganho.getPersonagem().getId()); // Ambos são Integer
        }
        return response;
    }

    // Método Privado: Converte DTO Request -> Entity
    private Ganho convertToEntity(GanhoDTORequest request) {
        Ganho ganho = new Ganho();
        ganho.setOuro(request.getOuro());
        ganho.setXp(request.getXp());
        ganho.setNivel(request.getNivel());
        ganho.setVida(request.getVida());
        ganho.setStatus(request.getStatus());
        return ganho;
    }

    // --- Métodos CRUD com Apagado Lógico ---

    @Transactional
    public GanhoDTOResponse criarGanho(GanhoDTORequest request) {
        // Busca Personagem ativo (Personagem.id é Integer)
        Personagem personagem = personagemRepository.findById(request.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));

        Ganho ganho = convertToEntity(request);
        ganho.setPersonagem(personagem);

        Ganho savedGanho = ganhoRepository.save(ganho);
        return convertToDto(savedGanho);
    }

    @Transactional(readOnly = true)
    public List<GanhoDTOResponse> listarGanhos() {
        return ganhoRepository.listarAtivos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GanhoDTOResponse listarPorId(Integer id) {
        // Usa o findById customizado (filtra status >= 0)
        Ganho ganho = ganhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ganho ativo com ID " + id + " não encontrado."));
        return convertToDto(ganho);
    }

    @Transactional
    public GanhoDTOResponse atualizarGanho(Integer id, GanhoDTORequest request) {
        // Usa o findById customizado (filtra status >= 0)
        Ganho ganho = ganhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ganho ativo com ID " + id + " não encontrado para atualização."));

        // Se o Personagem for alterado (opcional)
        if (!ganho.getPersonagem().getId().equals(request.getPersonagemId())) {
            Personagem novoPersonagem = personagemRepository.findById(request.getPersonagemId())
                    .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));
            ganho.setPersonagem(novoPersonagem);
        }

        // Aplica as atualizações do DTO
        ganho.setOuro(request.getOuro());
        ganho.setXp(request.getXp());
        ganho.setNivel(request.getNivel());
        ganho.setVida(request.getVida());
        ganho.setStatus(request.getStatus());

        Ganho updatedGanho = ganhoRepository.save(ganho);
        return convertToDto(updatedGanho);
    }

    @Transactional
    public void deletarGanho(Integer id) {
        // Busca ativa necessária para confirmar que o registro existe e não está 'apagado'
        if (!ganhoRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Ganho ativo com ID " + id + " não encontrado para deleção lógica.");
        }
        // Usa o método apagarLogico
        ganhoRepository.apagarLogico(id);
    }
}