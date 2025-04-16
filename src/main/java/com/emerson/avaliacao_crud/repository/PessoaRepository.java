package com.emerson.avaliacao_crud.repository;

import com.emerson.avaliacao_crud.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
}
