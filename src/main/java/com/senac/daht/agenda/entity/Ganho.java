package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ganho")
public class Ganho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ganho_id")
    private Integer id; // Padronizado para Integer

    @Column(name = "ganho_ouro")
    private Double ouro;

    @Column(name = "ganho_xp")
    private Double xp;

    @Column(name = "ganho_nivel")
    private Integer nivel;

    @Column(name = "ganho_vida")
    private Double vida;

    // Adicionado o campo STATUS para o Apagado Lógico
    @Column(name = "ganho_status")
    private Integer status;

    // Relacionamento ManyToOne com Personagem (Personagem.id é Integer)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;

    // --- Getters e Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Double getOuro() { return ouro; }
    public void setOuro(Double ouro) { this.ouro = ouro; }
    public Double getXp() { return xp; }
    public void setXp(Double xp) { this.xp = xp; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public Double getVida() { return vida; }
    public void setVida(Double vida) { this.vida = vida; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
}