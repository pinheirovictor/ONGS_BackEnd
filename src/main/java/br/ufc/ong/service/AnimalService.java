package br.ufc.ong.service;

import br.ufc.ong.model.Animal;
import br.ufc.ong.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }
}
