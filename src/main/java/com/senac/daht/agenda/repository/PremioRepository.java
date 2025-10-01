package com.senac.daht.agenda.repository;

import com.senac.daht.agenda.entity.Premio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> { // PK: Integer

    // 1. Apagado Lógico (UPDATE status = -1)
    // Usa o campo status que é Integer
    @Modifying
    @Transactional
    @Query("UPDATE Premio p SET p.status = -1 WHERE p.id = :id")
    void apagarLogico(@Param("id") Integer id);

    // 2. Listar Todos Ativos
    @Query("SELECT p FROM Premio p WHERE p.status >= 0")
    List<Premio> listarAtivos();

    // 3. Buscar por ID Ativo (Sobrescreve findById com filtro de status)
    @Query("SELECT p FROM Premio p WHERE p.id = :id AND p.status >= 0")
    Optional<Premio> findById(@Param("id") Integer id);
}