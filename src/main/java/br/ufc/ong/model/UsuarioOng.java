//package br.ufc.ong.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//
//@Entity
//@JsonIgnoreProperties(value = {"authorities"}, allowGetters = true)
//public class UsuarioOng {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nome;
//
//    @NotEmpty
//    @Column(unique = true)
//    private String email;
//
//    @NotEmpty
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String password;
//
//    @JsonBackReference
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "papel_usuario")
//    @Column(name = "papel")
//    private List<Papel> papeis;
//
//
//    public UsuarioOng(){
//
//    }
//
//    public UsuarioOng(Long id, String nome, String email, String password, List<Papel> papeis) {
//        this.id = id;
//        this.nome = nome;
//        this.email = email;
//        this.password = password;
//        this.papeis = papeis;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.papeis;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public List<String> getRoles() {
//        return new ArrayList<>();
//    }
//
//    public List<Papel> getPapeis() {
//        return papeis;
//    }
//
//    public void setPapeis(List<Papel> papeis) {
//        this.papeis = papeis;
//    }
//
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        UsuarioOng other = (UsuarioOng) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//}
