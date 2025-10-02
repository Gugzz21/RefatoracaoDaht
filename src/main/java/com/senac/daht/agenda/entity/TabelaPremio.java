package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tabelapremio")
public class TabelaPremio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tabelapremio_id")
    private Integer id;
    @Column(name = "tabelapremio_status")
    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "premio_id", nullable = false)
    private Premio premio;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
    public Premio getPremio() { return premio; }
    public void setPremio(Premio premio) { this.premio = premio; }
}