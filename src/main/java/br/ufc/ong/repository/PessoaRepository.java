package br.ufc.ong.repository;

import br.ufc.ong.model.Pessoa;
import br.ufc.ong.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public Pessoa findByUsuario(Usuario usuario);

}
