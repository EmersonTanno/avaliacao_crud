package com.emerson.avaliacao_crud.controller;

import com.emerson.avaliacao_crud.dto.PessoaDTO;
import com.emerson.avaliacao_crud.model.Pessoa;
import com.emerson.avaliacao_crud.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        List<PessoaDTO> list = pessoaService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        if (pessoaDTO.getCpf() == null || pessoaDTO.getIdade() <= 0) {
            return ResponseEntity.badRequest().body("CPF ou IDADE da Pessoa é inválido ou não informado.");
        }

        pessoaService.create(pessoaDTO);
        return ResponseEntity.ok("Pessoa cadastrada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPessoaPorId(@PathVariable Long id) {
        Optional<PessoaDTO> pessoaOpt = pessoaService.findById(id);
        return pessoaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        try {
            PessoaDTO pessoaAtualizada = pessoaService.update(id, pessoaDTO);
            return ResponseEntity.ok(pessoaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.ok("Pessoa removida com sucesso.");
    }
}
