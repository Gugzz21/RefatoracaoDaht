package com.senac.daht.agenda.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PersonagemDTORequest {

    @NotBlank
    private String nickname;

    @NotNull
    private Double vida;

    @NotNull
    @PositiveOrZero
    private Double ouro;

    @NotNull
    @PositiveOrZero
    private Double xp;

    @NotNull
    @PositiveOrZero
    private Integer nivel;

    @NotNull
    private Integer status;

    // ID do Usuário ao qual este personagem será ligado (FK)
    @NotNull
    private Integer usuarioId;

    // Getters e Setters
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public Double getVida() { return vida; }
    public void setVida(Double vida) { this.vida = vida; }
    public Double getOuro() { return ouro; }
    public void setOuro(Double ouro) { this.ouro = ouro; }
    public Double getXp() { return xp; }
    public void setXp(Double xp) { this.xp = xp; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
}