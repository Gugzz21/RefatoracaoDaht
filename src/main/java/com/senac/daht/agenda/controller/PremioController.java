package com.senac.daht.agenda.controller;

import com.senac.daht.agenda.dto.request.PremioDTORequest;
import com.senac.daht.agenda.dto.response.PremioDTOResponse;
import com.senac.daht.agenda.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name = "Prêmio", description = "API para o gerenciamento de prêmios (Com Apagado Lógico)")
public class PremioController {

    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar prêmios ativos", description = "Retorna todos os registros de prêmios que não foram deletados logicamente.")
    public ResponseEntity<List<PremioDTOResponse>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar prêmio ativo por ID", description = "Busca um prêmio pelo seu ID, retornando apenas se estiver ativo.")
    public ResponseEntity<PremioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        PremioDTOResponse premio = premioService.listarPorId(id);
        return ResponseEntity.ok(premio);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo prêmio", description = "Cria um novo registro de prêmio.")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse novoPremio = premioService.criarPremio(premioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPremio);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar prêmio", description = "Atualiza os dados de um prêmio ativo pelo seu ID.")
    public ResponseEntity<PremioDTOResponse> atualizarPremio(
            @PathVariable("id") Integer id,
            @Valid @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse premioAtualizado = premioService.atualizarPremio(id, premioDTORequest);
        return ResponseEntity.ok(premioAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar logicamente prêmio", description = "Define o status do prêmio como -1 (Apagado Lógico).")
    public ResponseEntity<Void> deletarPremio(@PathVariable("id") Integer id) {
        premioService.deletarPremio(id);
        return ResponseEntity.noContent().build();
    }
}