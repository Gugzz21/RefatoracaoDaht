package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

@Entity
@Table(name="table")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missao_id")
    private Integer id;

    @Column(name = "missao_nickname")
    private String nickname;

    @Column(name = "missao_vida")
    private Double vida;

    @Column(name = "missao_ouro")
    private Double ouro;

    @Column(name = "missao_xp")
    private Double xp;

    @Column(name = "missao_nivel")
    private int nivel;

    @Column(name = "missao_status")
    private Integer status;

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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
