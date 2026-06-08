package com.senac.daht.agenda.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;
import java.time.LocalDate;

public class UsuarioDTORequest {

    private String nome;

    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    // Fix #8: mínimo de 8 caracteres como segunda linha de defesa (frontend também valida)
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String senha;


    private Integer status;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}