package com.senac.daht.agenda.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class GanhoDTORequest {
    @NotNull @PositiveOrZero
    private Double ouro;
    @NotNull @PositiveOrZero
    private Double xp;
    @NotNull @PositiveOrZero
    private Integer nivel;
    @NotNull
    private Double vida;
    @NotNull
    private Integer status; // Necessário para a criação (status inicial)
    @NotNull
    private Integer personagemId; // Personagem.id é Integer

    // Getters e Setters
    public Double getOuro() { return ouro; }
    public void setOuro(Double ouro) { this.ouro = ouro; }
    public Double getXp() { return xp; }
    public void setXp(Double xp) { this.xp = xp; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public Double getVida() { return vida; }
    public void setVida(Double vida) { this.vida = vida; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getPersonagemId() { return personagemId; }
    public void setPersonagemId(Integer personagemId) { this.personagemId = personagemId; }
}