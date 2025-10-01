package com.senac.daht.agenda.repository;

import com.senac.daht.agenda.entity.Ganho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GanhoRepository extends JpaRepository<Ganho, Integer> {

    @Override
    List<Ganho> findAll();
    @Override
    Optional<Ganho> findById(Integer id);

}