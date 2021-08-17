package br.ufc.ong.repository;

import br.ufc.ong.model.Ocorrencia;
import br.ufc.ong.model.Ong;
import br.ufc.ong.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    List<Ocorrencia> findByPessoa(Pessoa pessoa);

    List<Ocorrencia> findByOng(Ong ong);
}
