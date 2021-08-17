package br.ufc.ong.repository;

import br.ufc.ong.model.Ong;

import br.ufc.ong.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OngRepository extends JpaRepository<Ong, Long> {
    public Ong findByUsuario(Usuario usuario);
}
