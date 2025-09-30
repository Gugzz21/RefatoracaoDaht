package com.senac.daht.agenda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tabelapremio")
public class TabelaPremio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tabelapremio_id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
