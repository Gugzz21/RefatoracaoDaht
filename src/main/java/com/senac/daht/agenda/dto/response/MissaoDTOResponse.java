package com.senac.daht.agenda.dto.response;
import java.sql.Date;
import java.time.LocalDate;

public class MissaoDTOResponse {
    private Integer id;
    private String descricao;
    private int repeticao;
    private Integer dificuldade;
    private LocalDate dataFinalizacao;
    private LocalDate dataInicio;
    private Integer status;
    private Long personagemId;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getRepeticao() { return repeticao; }
    public void setRepeticao(int repeticao) { this.repeticao = repeticao; }
    public Integer getDificuldade() { return dificuldade; }
    public void setDificuldade(Integer dificuldade) { this.dificuldade = dificuldade; }
    public LocalDate getDataFinalizacao() { return dataFinalizacao; }
    public void setDataFinalizacao(LocalDate dataFinalizacao) { this.dataFinalizacao = dataFinalizacao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Long getPersonagemId() { return personagemId; }
    public void setPersonagemId(Long personagemId) { this.personagemId = personagemId; }
}