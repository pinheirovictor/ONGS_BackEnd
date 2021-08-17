package br.ufc.ong.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {

    USUARIO_NORMAL("Pessoa"), USUARIO_ONG("Ong"),
    ADMIN("Administrador");

    private String nome;

    Papel(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }

}
