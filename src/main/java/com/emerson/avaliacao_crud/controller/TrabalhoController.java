package com.emerson.avaliacao_crud.controller;

import com.emerson.avaliacao_crud.dto.TrabalhoDTO;
import com.emerson.avaliacao_crud.model.Pessoa;
import com.emerson.avaliacao_crud.repository.PessoaRepository;
import com.emerson.avaliacao_crud.service.PessoaService;
import com.emerson.avaliacao_crud.service.TrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trabalho")
public class TrabalhoController {

    @Autowired
    private TrabalhoService trabalhoService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<TrabalhoDTO>> listarTrabalhos() {
        List<TrabalhoDTO> list = trabalhoService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> criarTrabalho(@RequestBody TrabalhoDTO trabalhoDTO) {
        if (trabalhoDTO.getNome() == null || trabalhoDTO.getEndereco() == null) {
            return ResponseEntity.badRequest().body("Nome ou Endereço não informado.");
        }

        trabalhoService.create(trabalhoDTO);
        return ResponseEntity.ok("Trabalho cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTrabalhoPorId(@PathVariable Long id) {
        Optional<TrabalhoDTO> trabalhoOpt = trabalhoService.findById(id);
        return trabalhoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTrabalho(@PathVariable Long id, @RequestBody TrabalhoDTO trabalhoDTO) {
        try {
            TrabalhoDTO trabalhoAtualizado = trabalhoService.update(id, trabalhoDTO);
            return ResponseEntity.ok(trabalhoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTrabalho(@PathVariable Long id) {
        trabalhoService.delete(id);
        return ResponseEntity.ok("Trabalho removido com sucesso.");
    }


    @PutMapping("/adicionarPessoa/{trabalhoId}/{pessoaId}")
    public ResponseEntity<String> adicionarPessoaAoTrabalho(@PathVariable Long trabalhoId, @PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElse(null);

        if (pessoa == null) {
            return ResponseEntity.badRequest().body("Pessoa não encontrada.");
        }

        try {
            trabalhoService.adicionarPessoa(trabalhoId, pessoa);
            return ResponseEntity.ok("Pessoa adicionada ao trabalho com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
