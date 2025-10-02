package com.senac.daht.agenda.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "premio")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "premio_id")
    private Integer id;

    @Column(name = "premio_nome", length = 45)
    private String nome;

    @Column(name = "premio_preco")
    private Double preco;

    @Column(name = "premio_status")
    private Integer status;

    @OneToMany(mappedBy = "premio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TabelaPremio> premiosEmTabelas;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public List<TabelaPremio> getPremiosEmTabelas() { return premiosEmTabelas; }
    public void setPremiosEmTabelas(List<TabelaPremio> premiosEmTabelas) { this.premiosEmTabelas = premiosEmTabelas; }
}