package com.emerson.avaliacao_crud.service;

import com.emerson.avaliacao_crud.dto.PessoaDTO;
import com.emerson.avaliacao_crud.model.Pessoa;
import com.emerson.avaliacao_crud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(PessoaDTO dto)
    {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());

        return pessoaRepository.save(pessoa);
    }

    public List<PessoaDTO> findAll()
    {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> new PessoaDTO(pessoa.getId(), pessoa.getCpf(), pessoa.getIdade()))
                .collect(Collectors.toList());
    }

    public Optional<PessoaDTO> findById(Long id)
    {
        return pessoaRepository.findById(id).map(pessoa -> new PessoaDTO(pessoa.getId(), pessoa.getCpf(), pessoa.getIdade()));
    }

    public void delete(Long id)
    {
        pessoaRepository.deleteById(id);
    }

    public PessoaDTO update(Long id, PessoaDTO dto){
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrado"));

        if(dto.getCpf() != null)
        {
            pessoa.setCpf(dto.getCpf());
        }
        if(dto.getIdade() >= 0)
        {
            pessoa.setIdade(dto.getIdade());
        }

        pessoa = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoa.getId(), pessoa.getCpf(), pessoa.getIdade());
    }


}
