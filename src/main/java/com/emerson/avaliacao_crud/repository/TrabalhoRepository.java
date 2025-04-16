package com.emerson.avaliacao_crud.repository;

import com.emerson.avaliacao_crud.model.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
}
