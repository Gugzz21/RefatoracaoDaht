package com.senac.daht.agenda.repository;

import com.senac.daht.agenda.entity.Ganho;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// PK Type: Integer
public interface GanhoRepository extends JpaRepository<Ganho, Integer> {

    // 1. Apagado Lógico (Atualiza status = -1)
    @Modifying
    @Transactional
    @Query("UPDATE Ganho g SET g.status = -1 WHERE g.id = :id")
    void apagarLogico(@Param("id") Integer id);

    // 2. Listar Todos Ativos
    @Query("SELECT g FROM Ganho g WHERE g.status >= 0")
    List<Ganho> listarAtivos();

    // 3. Obter por ID Ativo (Sobrescreve findById com filtro de status)
    @Query("SELECT g FROM Ganho g WHERE g.id = :id AND g.status >= 0")
    Optional<Ganho> findById(@Param("id") Integer id);
}