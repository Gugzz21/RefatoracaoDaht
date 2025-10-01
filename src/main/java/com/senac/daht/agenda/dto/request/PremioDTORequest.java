package com.senac.daht.agenda.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PremioDTORequest {
    @NotBlank
    private String nome;
    @NotNull
    @PositiveOrZero
    private Double preco;
    @NotBlank
    private String statusComercial; // Status de negócio (VARCHAR)
    @NotNull
    private Integer status; // Status de controle (Integer)

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public String getStatusComercial() { return statusComercial; }
    public void setStatusComercial(String statusComercial) { this.statusComercial = statusComercial; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}