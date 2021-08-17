package br.ufc.ong.controller;

import br.ufc.ong.model.Pessoa;
import br.ufc.ong.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    // metodo salvar pessoas
    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok(pessoaService.save(pessoa));
    }

    // listar todas as pessoas
    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> find() {
        return ResponseEntity.ok(pessoaService.findAll());
    }


}
