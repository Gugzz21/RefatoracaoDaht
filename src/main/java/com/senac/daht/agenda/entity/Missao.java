package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "missao")
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missao_id")
    private Integer id;

    @Column(name = "missao_descricao")
    private String descricao;

    @Column(name = "missao_repeticao")
    private int repeticao;

    @Column(name = "missao_dificuldade")
    private int dificuldade;

    @Column(name = "missao_efeito")
    private int efeito;

    @Column(name = "missao_datainicio")
    private LocalDate datainicio;

    @Column(name = "missao_datafinalizacao")
    private LocalDate datafinalizacao;

    @Column(name = "missao_status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getEfeito() {
        return efeito;
    }

    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }

    public LocalDate getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(LocalDate datainicio) {
        this.datainicio = datainicio;
    }

    public LocalDate getDatafinalizacao() {
        return datafinalizacao;
    }

    public void setDatafinalizacao(LocalDate datafinalizacao) {
        this.datafinalizacao = datafinalizacao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

