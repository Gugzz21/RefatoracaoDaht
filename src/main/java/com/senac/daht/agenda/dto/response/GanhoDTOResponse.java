package com.senac.daht.agenda.dto.response;

public class GanhoDTOResponse {
    private Integer id;
    private Double ouro;
    private Double xp;
    private Integer nivel;
    private Double vida;
    private Integer status;
    private Integer personagemId; // Personagem.id é Integer

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
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