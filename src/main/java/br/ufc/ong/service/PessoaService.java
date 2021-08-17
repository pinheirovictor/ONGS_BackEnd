package br.ufc.ong.service;

import br.ufc.ong.model.Pessoa;
import br.ufc.ong.model.Usuario;
import br.ufc.ong.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder encoder;

    // metodo de salvar pessoa
    public Pessoa save(Pessoa pessoa){
        Usuario usuario = usuarioService.findByEmail(pessoa.getUsuario().getEmail());
        if(usuario != null){
            pessoa.setUsuario(usuario);
        }else{
            String password = encoder.encode(pessoa.getUsuario().getPassword());
            pessoa.getUsuario().setPassword(password);
        }
        return pessoaRepository.save(pessoa);
    }

    // metodo listar pessoas
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    // metodo buscar pessoa por id
    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // metodo de atualizar dados cadastrais da pessoa
    public Pessoa atualizarPessoa(Pessoa pessoa) {
        Pessoa pessoaAtualizada = this.pessoaRepository.getOne(pessoa.getId());
        pessoaAtualizada.getUsuario().setNome(pessoa.getUsuario().getNome());
        pessoaAtualizada.getUsuario().setSobrenome(pessoaAtualizada.getUsuario().getSobrenome());
        pessoaAtualizada.setTelefone(pessoa.getTelefone());
        pessoaAtualizada.setSexo(pessoa.getSexo());
        pessoaAtualizada.setEndereco(pessoa.getEndereco());

        return pessoaRepository.saveAndFlush(pessoaAtualizada);
    }
}
