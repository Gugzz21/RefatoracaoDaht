package com.senac.daht.agenda.repository;

import com.senac.daht.agenda.entity.Missao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Missao m SET m.status = -1 WHERE m.id = :id")
    void apagarLogico(@Param("id") Integer id);


    @Query("SELECT m FROM Missao m WHERE m.status >= 0")
    List<Missao> listarAtivos();

    @Query("SELECT m FROM Missao m WHERE m.id = :id AND m.status >= 0")
    Optional<Missao> findById(@Param("id") Integer id);
    @Query("SELECT m FROM Missao m WHERE m.personagem.id = :personagemId AND m.status >= 0")
    List<Missao> findByPersonagemIdAtivo(@Param("personagemId") Integer personagemId);
}