package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ganho")
public class Ganho {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ganho_id")
    private Integer id;

    @Column(name = "ganho_ouro")
    private Double ouro;

    @Column(name = "ganho_xp")
    private double xp;

    @Column(name = "ganho_nivel")
    private int nivel;

    @Column(name = "ganho_vida")
    private Double vida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOuro() {
        return ouro;
    }

    public void setOuro(Double ouro) {
        this.ouro = ouro;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }
}
