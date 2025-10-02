package com.senac.daht.agenda.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;
import java.time.LocalDate;

public class MissaoDTORequest {


    private String descricao;


    private int repeticao;


    private Integer dificuldade;


    private Integer efeito;

    private LocalDate dataFinalizacao;

    private LocalDate dataInicio;


    private Integer status;

    private Long personagemId;

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getRepeticao() { return repeticao; }
    public void setRepeticao(int repeticao) { this.repeticao = repeticao; }
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
    public Long getPersonagemId() { return personagemId; }
    public void setPersonagemId(Long personagemId) { this.personagemId = personagemId; }
}
