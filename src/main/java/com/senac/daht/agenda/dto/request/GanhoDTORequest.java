package com.senac.daht.agenda.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class GanhoDTORequest {

    private Double ouro;

    private Double xp;

    private Integer nivel;

    private Double vida;
    private Integer personagemId;

    public Double getOuro() { return ouro; }
    public void setOuro(Double ouro) { this.ouro = ouro; }
    public Double getXp() { return xp; }
    public void setXp(Double xp) { this.xp = xp; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public Double getVida() { return vida; }
    public void setVida(Double vida) { this.vida = vida; }
    public Integer getPersonagemId() { return personagemId; }
    public void setPersonagemId(Integer personagemId) { this.personagemId = personagemId; }
}