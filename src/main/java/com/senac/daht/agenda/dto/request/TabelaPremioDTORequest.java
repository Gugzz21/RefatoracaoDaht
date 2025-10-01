package com.senac.daht.agenda.dto.request;

import jakarta.validation.constraints.NotNull;

public class TabelaPremioDTORequest {
    @NotNull
    private Integer personagemId; // FK: Integer
    @NotNull
    private Integer premioId; // FK: Integer
    @NotNull
    private Integer status; // Status de controle

    // Getters e Setters
    public Integer getPersonagemId() { return personagemId; }
    public void setPersonagemId(Integer personagemId) { this.personagemId = personagemId; }
    public Integer getPremioId() { return premioId; }
    public void setPremioId(Integer premioId) { this.premioId = premioId; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}