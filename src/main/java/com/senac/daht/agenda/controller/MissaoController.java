package com.senac.daht.agenda.controller;

import com.senac.daht.agenda.dto.request.MissaoDTORequest;
import com.senac.daht.agenda.dto.response.MissaoDTOResponse;
import com.senac.daht.agenda.service.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missao")
@CrossOrigin(origins = "*")
@Tag(name = "Missão", description = "API para o gerenciamento de missões de personagem (Com Apagado Lógico)")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar missões ativas", description = "Retorna todos os registros de missões que não foram deletadas logicamente.")
    public ResponseEntity<List<MissaoDTOResponse>> listarMissoes() {
        return ResponseEntity.ok(missaoService.listarMissoes());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar missão ativa por ID", description = "Busca uma missão pelo seu ID, retornando apenas se estiver ativa.")
    public ResponseEntity<MissaoDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        MissaoDTOResponse missao = missaoService.listarPorId(id);
        return ResponseEntity.ok(missao);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova missão", description = "Cria um novo registro de missão para um personagem.")
    public ResponseEntity<MissaoDTOResponse> criarMissao(@Valid @RequestBody MissaoDTORequest missaoDTORequest) {
        MissaoDTOResponse novaMissao = missaoService.criarMissao(missaoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMissao);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar missão", description = "Atualiza os dados de uma missão ativa pelo seu ID.")
    public ResponseEntity<MissaoDTOResponse> atualizarMissao(
            @PathVariable("id") Integer id,
            @Valid @RequestBody MissaoDTORequest missaoDTORequest) {
        MissaoDTOResponse missaoAtualizada = missaoService.atualizarMissao(id, missaoDTORequest);
        return ResponseEntity.ok(missaoAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar logicamente missão", description = "Define o status da missão como -1 (Apagado Lógico).")
    public ResponseEntity<Void> deletarMissao(@PathVariable("id") Integer id) {
        missaoService.deletarMissao(id);
        return ResponseEntity.noContent().build();
    }
}