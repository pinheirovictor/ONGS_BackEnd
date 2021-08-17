package br.ufc.ong.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocorrencia")
    private Long id;

    private String titulo;

    private String descricao;

    private String endereco;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;

    @OneToOne
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Ocorrencia() {
    }

    public Ocorrencia(Long id, String titulo, String descricao, String endereco, Status status, Ong ong, Animal animal, Pessoa pessoa) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.status = status;
        this.ong = ong;
        this.animal = animal;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ocorrencia ocorrencia = (Ocorrencia) o;
        return id.equals(ocorrencia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
