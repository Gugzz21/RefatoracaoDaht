package com.senac.daht.agenda.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PremioDTORequest {

    private String nome;

    private Double preco;

    private Integer status;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}