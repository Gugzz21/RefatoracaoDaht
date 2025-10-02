package com.senac.daht.agenda.dto.request;

import jakarta.validation.constraints.NotNull;

public class TabelaPremioDTORequest {

    private Integer personagemId;

    private Integer premioId;

    @NotNull
    private Integer status;

    public Integer getPersonagemId() { return personagemId; }
    public void setPersonagemId(Integer personagemId) { this.personagemId = personagemId; }
    public Integer getPremioId() { return premioId; }
    public void setPremioId(Integer premioId) { this.premioId = premioId; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}