package br.ufc.ong.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "animal")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long id;

    private String nome;

    private String especie;

    private String raça;

    private String caracteristicas;

    public Animal() {
    }

    public Animal(Long id, String nome, String especie, String raça, String caracteristicas) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raça = raça;
        this.caracteristicas = caracteristicas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
