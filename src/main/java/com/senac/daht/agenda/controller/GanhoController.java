package com.senac.daht.agenda.controller;

import com.senac.daht.agenda.dto.request.GanhoDTORequest;
import com.senac.daht.agenda.dto.response.GanhoDTOResponse;
import com.senac.daht.agenda.service.GanhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ganho")
@CrossOrigin(origins = "*")
@Tag(name = "Ganho", description = "API para o gerenciamento de ganhos de personagem (Com Apagado Lógico)")
public class GanhoController {

    private final GanhoService ganhoService;

    public GanhoController(GanhoService ganhoService) {
        this.ganhoService = ganhoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar ganhos ativos", description = "Retorna todos os registros de ganhos que não foram deletados logicamente.")
    public ResponseEntity<List<GanhoDTOResponse>> listarGanhos() {
        return ResponseEntity.ok(ganhoService.listarGanhos());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar ganho ativo por ID", description = "Busca um ganho pelo seu ID, retornando apenas se estiver ativo.")
    public ResponseEntity<GanhoDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        GanhoDTOResponse ganho = ganhoService.listarPorId(id);
        return ResponseEntity.ok(ganho);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo ganho", description = "Cria um novo registro de ganho para um personagem.")
    public ResponseEntity<GanhoDTOResponse> criarGanho(@Valid @RequestBody GanhoDTORequest ganhoDTORequest) {
        GanhoDTOResponse novoGanho = ganhoService.criarGanho(ganhoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGanho);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar ganho", description = "Atualiza os dados de um ganho ativo pelo seu ID.")
    public ResponseEntity<GanhoDTOResponse> atualizarGanho(
            @PathVariable("id") Integer id,
            @Valid @RequestBody GanhoDTORequest ganhoDTORequest) {
        GanhoDTOResponse ganhoAtualizado = ganhoService.atualizarGanho(id, ganhoDTORequest);
        return ResponseEntity.ok(ganhoAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar logicamente ganho", description = "Define o status do ganho como -1 (Apagado Lógico).")
    public ResponseEntity<Void> deletarGanho(@PathVariable("id") Integer id) {
        ganhoService.deletarGanho(id);
        return ResponseEntity.noContent().build();
    }
}