package com.senac.daht.agenda.service;

import com.senac.daht.agenda.dto.request.PremioDTORequest;
import com.senac.daht.agenda.dto.response.PremioDTOResponse;
import com.senac.daht.agenda.entity.Premio;
import com.senac.daht.agenda.repository.PremioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremioService {

    private final PremioRepository premioRepository;

    @Autowired
    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    // --- Métodos de Conversão ---
    private PremioDTOResponse convertToDto(Premio premio) {
        PremioDTOResponse response = new PremioDTOResponse();
        response.setId(premio.getId());
        response.setNome(premio.getNome());
        response.setPreco(premio.getPreco());
        response.setStatus(premio.getStatus());
        // Adicione o status se estiver no DTO Response
        // response.setStatus(premio.getStatus());
        return response;
    }

    private Premio convertToEntity(PremioDTORequest request) {
        Premio premio = new Premio();
        premio.setNome(request.getNome());
        premio.setPreco(request.getPreco());
        premio.setStatus(request.getStatus());
        return premio;
    }

    // --- Métodos CRUD com Apagado Lógico ---

    @Transactional
    public PremioDTOResponse criarPremio(PremioDTORequest request) {
        Premio premio = convertToEntity(request);
        Premio savedPremio = premioRepository.save(premio);
        return convertToDto(savedPremio);
    }

    @Transactional(readOnly = true)
    public List<PremioDTOResponse> listarPremios() {
        return premioRepository.listarAtivos().stream() // Usando o método customizado
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PremioDTOResponse listarPorId(Integer id) {
        Premio premio = premioRepository.findById(id) // Usando o findById customizado
                .orElseThrow(() -> new EntityNotFoundException("Prêmio ativo com ID " + id + " não encontrado."));
        return convertToDto(premio);
    }

    @Transactional
    public PremioDTOResponse atualizarPremio(Integer id, PremioDTORequest request) {
        Premio premio = premioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prêmio ativo com ID " + id + " não encontrado para atualização."));

        // Aplica as atualizações do DTO
        premio.setNome(request.getNome());
        premio.setPreco(request.getPreco());
        premio.setStatus(request.getStatus()); // Assumindo que status pode ser atualizado

        Premio updatedPremio = premioRepository.save(premio);
        return convertToDto(updatedPremio);
    }

    @Transactional
    public void deletarPremio(Integer id) {
        if (!premioRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Prêmio ativo com ID " + id + " não encontrado para deleção lógica.");
        }
        premioRepository.apagarLogico(id);
    }
}