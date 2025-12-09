package com.senac.daht.agenda.controller;

import com.senac.daht.agenda.dto.LoginUserDto;
import com.senac.daht.agenda.dto.RecoveryJwtTokenDto;
import com.senac.daht.agenda.dto.request.UsuarioDTORequest;
import com.senac.daht.agenda.dto.response.UsuarioDTOResponse;
import com.senac.daht.agenda.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
@Tag(name = "Usuário", description = "API para o gerenciamento de usuários (Com Apagado Lógico)")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuários ativos", description = "Retorna todos os registros de usuários que não foram deletados logicamente.")
    public ResponseEntity<List<UsuarioDTOResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarAtivos());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar usuário ativo por ID", description = "Busca um usuário pelo seu ID, retornando apenas se estiver ativo.")
    public ResponseEntity<UsuarioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        UsuarioDTOResponse usuario = usuarioService.listarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo usuário", description = "Cria um novo registro de usuário.")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@Valid @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse novoUsuario = usuarioService.criarUsuario(usuarioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário ativo pelo seu ID.")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTORequest);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar logicamente usuário", description = "Define o status do usuário como -1 (Apagado Lógico).")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Integer id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = usuarioService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}