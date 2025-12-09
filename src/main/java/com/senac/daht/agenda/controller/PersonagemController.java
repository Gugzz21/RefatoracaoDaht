package com.senac.daht.agenda.controller;

import com.senac.daht.agenda.dto.request.PersonagemDTORequest;
import com.senac.daht.agenda.dto.response.PersonagemDTOResponse;
import com.senac.daht.agenda.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personagem")
@CrossOrigin(origins = "*")
@Tag(name = "Personagem", description = "API para o gerenciamento de personagens (Com Apagado Lógico)")
public class PersonagemController {

    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar personagens ativos", description = "Retorna todos os registros de personagens que não foram deletados logicamente.")
    public ResponseEntity<List<PersonagemDTOResponse>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarPersonagens());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar personagem ativo por ID", description = "Busca um personagem pelo seu ID, retornando apenas se estiver ativo.")
    public ResponseEntity<PersonagemDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        PersonagemDTOResponse personagem = personagemService.listarPorId(id);
        return ResponseEntity.ok(personagem);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo personagem", description = "Cria um novo registro de personagem.")
    public ResponseEntity<PersonagemDTOResponse> criarPersonagem(
            @Valid @RequestBody PersonagemDTORequest personagemDTORequest) {
        PersonagemDTOResponse novoPersonagem = personagemService.criarPersonagem(personagemDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPersonagem);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar personagem", description = "Atualiza os dados de um personagem ativo pelo seu ID.")
    public ResponseEntity<PersonagemDTOResponse> atualizarPersonagem(
            @PathVariable("id") Integer id,
            @Valid @RequestBody PersonagemDTORequest personagemDTORequest) {
        PersonagemDTOResponse personagemAtualizado = personagemService.atualizarPersonagem(id, personagemDTORequest);
        return ResponseEntity.ok(personagemAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar logicamente personagem", description = "Define o status do personagem como -1 (Apagado Lógico).")
    public ResponseEntity<Void> deletarPersonagem(@PathVariable("id") Integer id) {
        personagemService.deletarPersonagem(id);
        return ResponseEntity.noContent().build();
    }
}