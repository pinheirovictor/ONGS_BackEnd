package br.ufc.ong.config;

import br.ufc.ong.model.Usuario;
import br.ufc.ong.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustom implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = usuarioService.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário e/ou senha inválidos");
        } else {
            return usuario;
        }
    }
}