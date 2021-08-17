package br.ufc.ong.service;

import br.ufc.ong.model.Ong;
import br.ufc.ong.model.Usuario;
import br.ufc.ong.repository.OngRepository;
import br.ufc.ong.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.List;

@Service
public class OngService {

    @Autowired
    OngRepository ongRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder encoder;


    // metodo salvar ong
    public Ong save(Ong ong){
        Usuario usuario1 = usuarioService.findByEmail(ong.getUsuario().getEmail());
        if(usuario1 != null){
            ong.setUsuario(usuario1);
        }else{
            String password = encoder.encode(ong.getUsuario().getPassword());
            ong.getUsuario().setPassword(password);
        }
        return  ongRepository.save(ong);
    }

    // metdo listar ongs
    public List<Ong> findAll() {
        return ongRepository.findAll();
    }

    // metodo buscar ong por id
    public Ong findById(Long id) {
        return ongRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // metodo de atualizar dados cadastrais da ong
    public Ong atualizarOng(Ong ong) {
        Ong ongAtualizada = this.ongRepository.getOne(ong.getId());
        ongAtualizada.setNomeOng(ong.getNomeOng());
        ongAtualizada.setTelefone(ong.getTelefone());
        ong.setEndereco(ong.getEndereco());

        return ongRepository.saveAndFlush(ongAtualizada);
    }

}
