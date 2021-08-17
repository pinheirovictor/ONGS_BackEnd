package br.ufc.ong.controller;

import br.ufc.ong.model.Usuario;
import br.ufc.ong.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    PasswordEncoder encoder;


    // buscar por email
    @GetMapping("/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }

    // listar todos os usuários
    @GetMapping("")
    public ResponseEntity<List<Usuario>> find() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // crirar um usuário
    @PostMapping("")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    // deletar um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // metodo atualizar senha do usuário
    @PutMapping("updatePassword")
    public ResponseEntity<Usuario> updatePassword(@RequestBody Map<String, String> map){
        return ResponseEntity.ok(usuarioService.updatePassword(map.get("senha"), map.get("newSenha")));
    }
}
