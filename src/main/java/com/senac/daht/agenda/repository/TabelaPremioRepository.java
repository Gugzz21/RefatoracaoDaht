package com.senac.daht.agenda.repository;

import com.senac.daht.agenda.entity.TabelaPremio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabelaPremioRepository extends JpaRepository<TabelaPremio, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE TabelaPremio tp SET tp.status = -1 WHERE tp.id = :id")
    void apagarLogico(@Param("id") Integer id);

    @Query("SELECT tp FROM TabelaPremio tp WHERE tp.status >= 0")
    List<TabelaPremio> listarAtivos();

    @Query("SELECT tp FROM TabelaPremio tp WHERE tp.id = :id AND tp.status >= 0")
    Optional<TabelaPremio> findById(@Param("id") Integer id);

    @Query("SELECT tp FROM TabelaPremio tp WHERE tp.personagem.id = :personagemId AND tp.status >= 0")
    List<TabelaPremio> findByPersonagemIdAtivo(@Param("personagemId") Integer personagemId);
}