package br.ufc.ong.controller;

import br.ufc.ong.model.Ong;
import br.ufc.ong.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ongs")
public class OngController {

    @Autowired
    OngService ongService;

    // metodo de salvar ong
    @PostMapping()
    public ResponseEntity<Ong> save(@RequestBody Ong ong){
        return ResponseEntity.ok(ongService.save(ong));
    }

    // metodo listar ongs
    @GetMapping("/listar")
    public ResponseEntity<List<Ong>> findAll() {

        return ResponseEntity.ok(ongService.findAll());
    }

    // metodo buscar ong por id
    @GetMapping({ "/listar/{id}" })
    public ResponseEntity<Ong> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ongService.findById(id));
    }

    // metodo de atualizar ong
    @PutMapping({ "/atualizar/{id}" })
    public ResponseEntity<List<Ong>> atualizarOng(@RequestBody Ong ong) {
        ongService.atualizarOng(ong);
        return ResponseEntity.ok().build();
    }
}
