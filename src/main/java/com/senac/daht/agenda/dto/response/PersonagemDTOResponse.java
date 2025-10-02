package com.senac.daht.agenda.dto.response;

public class PersonagemDTOResponse {
    private Long id;
    private String nickname;
    private Double vida;
    private Double ouro;
    private Double xp;
    private Integer nivel;
    private Integer status;
    private Integer usuarioId;
    private String nomeUsuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
}