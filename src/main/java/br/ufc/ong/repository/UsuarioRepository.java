package br.ufc.ong.repository;

import br.ufc.ong.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
