package br.ufc.ong.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ong")
public class Ong implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ong")
    private Long id;

    @NotEmpty()
    private String nomeOng;

    @NotEmpty()
    private String sigla;

    private String telefone;

    private String endereco;

    private String finalidade;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Ong(){

    }

    public Ong(Long id, String nomeOng, String sigla, String telefone, String endereco, String finalidade, Usuario usuario) {
        this.id = id;
        this.nomeOng = nomeOng;
        this.sigla = sigla;
        this.telefone = telefone;
        this.endereco = endereco;
        this.finalidade = finalidade;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeOng() {
        return nomeOng;
    }

    public void setNomeOng(String nomeOng) {
        this.nomeOng = nomeOng;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ong ong = (Ong) o;
        return id.equals(ong.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
