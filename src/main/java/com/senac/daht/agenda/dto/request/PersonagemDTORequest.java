package com.senac.daht.agenda.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PersonagemDTORequest {


    private String nickname;


    private Double vida;


    private Double ouro;


    private Double xp;


    private Integer nivel;

    private Integer status;

    private Integer usuarioId;

    private Integer molduraId;
    private Integer cabecaId;
    private Integer maoId;

    public Integer getMolduraId() {
        return molduraId;
    }

    public void setMolduraId(Integer molduraId) {
        this.molduraId = molduraId;
    }

    public Integer getCabecaId() {
        return cabecaId;
    }

    public void setCabecaId(Integer cabecaId) {
        this.cabecaId = cabecaId;
    }

    public Integer getMaoId() {
        return maoId;
    }

    public void setMaoId(Integer maoId) {
        this.maoId = maoId;
    }

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