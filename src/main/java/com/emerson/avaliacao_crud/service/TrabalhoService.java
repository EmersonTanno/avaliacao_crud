package com.emerson.avaliacao_crud.service;

import com.emerson.avaliacao_crud.dto.PessoaDTO;
import com.emerson.avaliacao_crud.dto.TrabalhoDTO;
import com.emerson.avaliacao_crud.model.Pessoa;
import com.emerson.avaliacao_crud.model.Trabalho;
import com.emerson.avaliacao_crud.repository.PessoaRepository;
import com.emerson.avaliacao_crud.repository.TrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrabalhoService {

    @Autowired
    private TrabalhoRepository trabalhoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Trabalho create(TrabalhoDTO dto) {
        Trabalho trabalho = new Trabalho();
        trabalho.setNome(dto.getNome());
        trabalho.setEndereco(dto.getEndereco());
        return trabalhoRepository.save(trabalho);
    }

    public List<TrabalhoDTO> findAll() {
        return trabalhoRepository.findAll().stream()
                .map(trabalho -> new TrabalhoDTO(trabalho.getId(), trabalho.getNome(), trabalho.getEndereco()))
                .collect(Collectors.toList());
    }

    public Optional<TrabalhoDTO> findById(Long id) {
        return trabalhoRepository.findById(id)
                .map(trabalho -> new TrabalhoDTO(trabalho.getId(), trabalho.getNome(), trabalho.getEndereco()));
    }

    public void delete(Long id) {
        Trabalho trabalho = trabalhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabalho não encontrado"));

        for (Pessoa pessoa : trabalho.getPessoas()) {
            pessoa.setTrabalho(null);
            pessoaRepository.save(pessoa);
        }

        trabalhoRepository.delete(trabalho);
    }

    public TrabalhoDTO update(Long id, TrabalhoDTO dto) {
        Trabalho trabalho = trabalhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabalho não encontrado"));

        if (dto.getNome() != null) {
            trabalho.setNome(dto.getNome());
        }
        if (dto.getEndereco() != null) {
            trabalho.setEndereco(dto.getEndereco());
        }

        trabalho = trabalhoRepository.save(trabalho);
        return new TrabalhoDTO(trabalho.getId(), trabalho.getNome(), trabalho.getEndereco());
    }

    public void adicionarPessoa(Long id, Pessoa pessoa) {
        Trabalho trabalho = trabalhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabalho não encontrado"));

        pessoa.setTrabalho(trabalho);
        trabalho.getPessoas().add(pessoa);

        trabalhoRepository.save(trabalho);
    }

    public List<PessoaDTO> listarPessoasPorTrabalho(Long trabalhoId) {
        Trabalho trabalho = trabalhoRepository.findById(trabalhoId)
                .orElseThrow(() -> new RuntimeException("Trabalho não encontrado"));

        return trabalho.getPessoas().stream()
                .map(pessoa -> new PessoaDTO(pessoa.getId(), pessoa.getCpf(), pessoa.getIdade()))
                .collect(Collectors.toList());
    }
}
