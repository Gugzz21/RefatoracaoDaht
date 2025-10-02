package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "missao")
public class Missao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missao_id")
    private Integer id;

    @Column(name = "missao_descricao", length = 250)
    private String descricao;

    @Column(name = "missao_repeticao")
    private Integer repeticao;

    @Column(name = "missao_dificuldade")
    private Integer dificuldade;

    @Column(name = "missao_efeito")
    private Integer efeito;

    @Column(name = "missao_datafinalizacao")
    private LocalDate dataFinalizacao;

    @Column(name = "missao_datainicio")
    private LocalDate dataInicio;

    @Column(name = "missao_status")
    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getRepeticao() { return repeticao; }
    public void setRepeticao(Integer repeticao) { this.repeticao = repeticao; }

    public Integer getDificuldade() { return dificuldade; }
    public void setDificuldade(Integer dificuldade) { this.dificuldade = dificuldade; }
    public Integer getEfeito() { return efeito; }
    public void setEfeito(Integer efeito) { this.efeito = efeito; }
    public LocalDate getDataFinalizacao() { return dataFinalizacao; }
    public void setDataFinalizacao(LocalDate dataFinalizacao) { this.dataFinalizacao = dataFinalizacao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
}