package br.ufc.ong.repository;

import br.ufc.ong.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
