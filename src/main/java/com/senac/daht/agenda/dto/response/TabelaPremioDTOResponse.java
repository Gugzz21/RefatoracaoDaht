package com.senac.daht.agenda.dto.response;

public class TabelaPremioDTOResponse {
    private Integer id;
    private Integer personagemId;
    private String nomePersonagem;
    private Integer premioId;
    private String nomePremio;
    private Integer status;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPersonagemId() { return personagemId; }
    public void setPersonagemId(Integer personagemId) { this.personagemId = personagemId; }
    public String getNomePersonagem() { return nomePersonagem; }
    public void setNomePersonagem(String nomePersonagem) { this.nomePersonagem = nomePersonagem; }
    public Integer getPremioId() { return premioId; }
    public void setPremioId(Integer premioId) { this.premioId = premioId; }
    public String getNomePremio() { return nomePremio; }
    public void setNomePremio(String nomePremio) { this.nomePremio = nomePremio; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}