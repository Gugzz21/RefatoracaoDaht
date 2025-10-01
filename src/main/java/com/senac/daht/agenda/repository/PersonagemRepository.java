package com.senac.daht.agenda.repository;


import com.senac.daht.agenda.entity.Personagem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Personagem p SET p.status = -1 WHERE p.id = :id")
    void apagarLogico(@Param("id") Integer id);

    @Query("SELECT p FROM Personagem p WHERE p.status >= 0")
    List<Personagem> listarAtivos();

    @Query("SELECT p FROM Personagem p WHERE p.id = :id AND p.status >= 0")
    Optional<Personagem> findById(@Param("id") Integer id);
}
