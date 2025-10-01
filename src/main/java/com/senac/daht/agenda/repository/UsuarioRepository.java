package com.senac.daht.agenda.repository;
import com.senac.daht.agenda.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.status = -1 WHERE u.id = :id")
    void apagarLogico(@Param("id") Integer id);

    @Query("SELECT u FROM Usuario u WHERE u.status >= 0")
    List<Usuario> listarAtivos();

    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.status >= 0")
    Optional<Usuario> findById(@Param("id") Integer id);
}