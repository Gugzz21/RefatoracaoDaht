package com.senac.daht.agenda.service;

import com.senac.daht.agenda.dto.request.TabelaPremioDTORequest;
import com.senac.daht.agenda.dto.response.TabelaPremioDTOResponse;
import com.senac.daht.agenda.entity.Personagem;
import com.senac.daht.agenda.entity.Premio;
import com.senac.daht.agenda.entity.TabelaPremio;
import com.senac.daht.agenda.repository.PersonagemRepository;
import com.senac.daht.agenda.repository.PremioRepository;
import com.senac.daht.agenda.repository.TabelaPremioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabelaPremioService {

    private final TabelaPremioRepository tabelaPremioRepository;
    private final PersonagemRepository personagemRepository;
    private final PremioRepository premioRepository;

    @Autowired
    public TabelaPremioService(TabelaPremioRepository tabelaPremioRepository, PersonagemRepository personagemRepository, PremioRepository premioRepository) {
        this.tabelaPremioRepository = tabelaPremioRepository;
        this.personagemRepository = personagemRepository;
        this.premioRepository = premioRepository;
    }
    private TabelaPremioDTOResponse convertToDto(TabelaPremio tabelaPremio) {
        TabelaPremioDTOResponse response = new TabelaPremioDTOResponse();
        response.setId(tabelaPremio.getId());
        response.setStatus(tabelaPremio.getStatus());

        if (tabelaPremio.getPersonagem() != null) {
            response.setPersonagemId(tabelaPremio.getPersonagem().getId());
            response.setNomePersonagem(tabelaPremio.getPersonagem().getNickname());
        }

        if (tabelaPremio.getPremio() != null) {
            response.setPremioId(tabelaPremio.getPremio().getId());
            response.setNomePremio(tabelaPremio.getPremio().getNome());
        }
        return response;
    }

    @Transactional
    public TabelaPremioDTOResponse criarTabelaPremio(TabelaPremioDTORequest request) {

        Personagem personagem = personagemRepository.findById(request.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem ativo com ID " + request.getPersonagemId() + " não encontrado."));

        Premio premio = premioRepository.findById(request.getPremioId())
                .orElseThrow(() -> new EntityNotFoundException("Prêmio ativo com ID " + request.getPremioId() + " não encontrado."));

        TabelaPremio tabelaPremio = new TabelaPremio();

        tabelaPremio.setPersonagem(personagem);
        tabelaPremio.setPremio(premio);


        tabelaPremio.setStatus(request.getStatus() != null ? request.getStatus() : 0);

        TabelaPremio savedTabelaPremio = tabelaPremioRepository.save(tabelaPremio);

        return convertToDto(savedTabelaPremio);
    }

    @Transactional(readOnly = true)
    public List<TabelaPremioDTOResponse> listarTodos() {
        return tabelaPremioRepository.listarAtivos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TabelaPremioDTOResponse listarPorId(Integer id) {
        TabelaPremio tabelaPremio = tabelaPremioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de TabelaPrêmio ativo com ID " + id + " não encontrado."));
        return convertToDto(tabelaPremio);
    }

    @Transactional
    public void deletarTabelaPremio(Integer id) {
        if (!tabelaPremioRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Registro de TabelaPrêmio ativo com ID " + id + " não encontrado para deleção lógica.");
        }
        tabelaPremioRepository.apagarLogico(id);
    }
}