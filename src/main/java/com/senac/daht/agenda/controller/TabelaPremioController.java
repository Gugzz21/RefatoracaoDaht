package com.senac.daht.agenda.controller;

import com.senac.daht.agenda.dto.request.TabelaPremioDTORequest;
import com.senac.daht.agenda.dto.response.TabelaPremioDTOResponse;
import com.senac.daht.agenda.service.TabelaPremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tabelapremio")
@Tag(name = "Tabela Prêmio", description = "API para o gerenciamento da relação Personagem-Prêmio (Com Apagado Lógico)")
public class TabelaPremioController {

    private final TabelaPremioService tabelaPremioService;

    public TabelaPremioController(TabelaPremioService tabelaPremioService) {
        this.tabelaPremioService = tabelaPremioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar relações ativas", description = "Retorna todos os registros ativos da relação Personagem-Prêmio.")
    public ResponseEntity<List<TabelaPremioDTOResponse>> listarTodos() {
        return ResponseEntity.ok(tabelaPremioService.listarTodos());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar relação ativa por ID", description = "Busca uma relação pelo seu ID, retornando apenas se estiver ativa.")
    public ResponseEntity<TabelaPremioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        TabelaPremioDTOResponse tabelaPremio = tabelaPremioService.listarPorId(id);
        return ResponseEntity.ok(tabelaPremio);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova relação", description = "Cria um novo registro de relação entre Personagem e Prêmio.")
    public ResponseEntity<TabelaPremioDTOResponse> criarTabelaPremio(@Valid @RequestBody TabelaPremioDTORequest tabelaPremioDTORequest) {
        TabelaPremioDTOResponse novaTabelaPremio = tabelaPremioService.criarTabelaPremio(tabelaPremioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTabelaPremio);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar logicamente relação", description = "Define o status da relação como -1 (Apagado Lógico).")
    public ResponseEntity<Void> deletarTabelaPremio(@PathVariable("id") Integer id) {
        tabelaPremioService.deletarTabelaPremio(id);
        return ResponseEntity.noContent().build();
    }
}