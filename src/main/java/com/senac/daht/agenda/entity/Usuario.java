package com.senac.daht.agenda.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "usuario_nome", length = 45)
    private String nome;

    @Column(name = "usuario_email", length = 45, unique = true)
    private String email;

    @Column(name = "usuario_telefone", length = 45)
    private String telefone;

    @Column(name = "usuario_datanascimento")
    private LocalDate dataNascimento; // Usando LocalDate

    @Column(name = "usuario_senha", length = 45)
    private String senha;

    @Column(name = "usuario_status")
    private Integer status;

    // Relacionamento OneToOne com Personagem (Lado inverso: Personagem tem a FK)
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Personagem personagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}