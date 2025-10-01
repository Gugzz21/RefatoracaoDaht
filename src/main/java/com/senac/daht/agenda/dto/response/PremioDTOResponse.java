package com.senac.daht.agenda.dto.response;

public class PremioDTOResponse {
    private Integer id;
    private String nome;
    private Double preco;
    private String statusComercial;
    private Integer status;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public String getStatusComercial() { return statusComercial; }
    public void setStatusComercial(String statusComercial) { this.statusComercial = statusComercial; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}