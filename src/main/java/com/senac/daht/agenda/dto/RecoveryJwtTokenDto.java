package com.senac.daht.agenda.dto;

/**
 * Payload retornado pelo endpoint POST /api/usuario/login.
 *
 * @param token       JWT de autenticação
 * @param usuarioId   ID do usuário autenticado
 * @param personagemId ID do personagem vinculado ao usuário (null se ainda não criado)
 */
public record RecoveryJwtTokenDto(
        String token,
        Integer usuarioId,
        Integer personagemId
) {
}
