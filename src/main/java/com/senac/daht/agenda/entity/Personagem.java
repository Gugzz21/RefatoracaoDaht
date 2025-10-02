package com.senac.daht.agenda.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personagem_id")
    private Integer id;

    @Column(name = "personagem_nickname", length = 30)
    private String nickname;

    @Column(name = "personagem_vida")
    private Double vida;

    @Column(name = "personagem_ouro")
    private Double ouro;

    @Column(name = "personagem_xp")
    private Double xp;

    @Column(name = "personagem_nivel")
    private Integer nivel;

    @Column(name = "personagem_status")
    private Integer status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ganho> ganhos;
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Missao> missoes;
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TabelaPremio> premiosConquistados;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public Double getOuro() {
        return ouro;
    }

    public void setOuro(Double ouro) {
        this.ouro = ouro;
    }

    public Double getXp() {
        return xp;
    }

    public void setXp(Double xp) {
        this.xp = xp;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Ganho> getGanhos() {
        return ganhos;
    }

    public void setGanhos(List<Ganho> ganhos) {
        this.ganhos = ganhos;
    }

    public List<Missao> getMissoes() {
        return missoes;
    }

    public void setMissoes(List<Missao> missoes) {
        this.missoes = missoes;
    }

    public List<TabelaPremio> getPremiosConquistados() {
        return premiosConquistados;
    }

    public void setPremiosConquistados(List<TabelaPremio> premiosConquistados) {
        this.premiosConquistados = premiosConquistados;
    }
}