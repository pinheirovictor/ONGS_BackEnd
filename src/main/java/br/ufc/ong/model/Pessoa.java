package br.ufc.ong.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;

    @NotEmpty()
    @Column(unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    private String telefone;

    private String sexo;

    private String endereco;

    @JsonIgnore
    @ManyToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorencias;

    public Pessoa() {}

    public Pessoa(Long id, String cpf, String telefone, String sexo, String endereco, List<Ocorrencia> ocorencias) {
        this.id = id;

        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.endereco = endereco;
        this.ocorencias = ocorencias;
    }

    public Pessoa(Long id, String cpf, Usuario usuario, String telefone, String sexo, String endereco, List<Ocorrencia> ocorencias) {
        this.id = id;
        this.cpf = cpf;
        this.usuario = usuario;
        this.telefone = telefone;
        this.sexo = sexo;
        this.endereco = endereco;
        this.ocorencias = ocorencias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Ocorrencia> getOcorencias() {
        return ocorencias;
    }

    public void setOcorencias(List<Ocorrencia> ocorencias) {
        this.ocorencias = ocorencias;
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
        Pessoa pessoa = (Pessoa) o;
        return id.equals(pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
