package br.ufc.ong.service;

import br.ufc.ong.exception.DataIntegrityException;
import br.ufc.ong.exception.NotFoundException;
import br.ufc.ong.model.*;
import br.ufc.ong.repository.AnimalRepository;
import br.ufc.ong.repository.OcorrenciaRepository;
import br.ufc.ong.repository.OngRepository;
import br.ufc.ong.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private OngService ongService;


    // metodo salvar ocorrencia
    public Ocorrencia save(Ocorrencia ocorrencia){
        Usuario usuario =  usuarioService.getUsuarioLogado();

        if(usuario.getPapeis().contains(Papel.USUARIO_NORMAL)){
            Pessoa pessoa = pessoaRepository.findByUsuario(usuario);
            ocorrencia.setPessoa(pessoa);

            Animal animal = animalRepository.save(ocorrencia.getAnimal());
            ocorrencia.setAnimal(animal);

            Ong ong = ongService.findById(ocorrencia.getOng().getId());
            if(ong == null || !ong.getUsuario().getPapeis().contains(Papel.USUARIO_ONG)){
                throw new DataIntegrityException("Erro! Verifique se os dados da  responsável estão corretos!");
            }
            ocorrencia.setOng(ong);

            ocorrencia.setTitulo(ocorrencia.getTitulo());
            ocorrencia.setEndereco(ocorrencia.getEndereco());
            ocorrencia.setDescricao(ocorrencia.getDescricao());

            ocorrencia.setStatus(Status.PENDENTE);

            return ocorrenciaRepository.save(ocorrencia);
        }else {
            throw new DataIntegrityException("Erro! Certifique-se se as informações estão " +
                    "todas preenchidas ou se você pode realizar esta operação!");
        }

    }

    // metodo listar ocorrencias por id da pessoa que cadastrou
    public List<Ocorrencia> listarOcorrenciasByPessoa(){
        Usuario usuario = usuarioService.getUsuarioLogado();
        Pessoa pessoa = pessoaRepository.findByUsuario(usuario);

        return ocorrenciaRepository.findByPessoa(pessoa);
    }

    // metodo listar ocorrencias por id da ong a qual ela pertence

    public List<Ocorrencia> listarOcorrenciasByOng(){
        Usuario usuario = usuarioService.getUsuarioLogado();
        Ong ong = ongRepository.findByUsuario(usuario);

        return ocorrenciaRepository.findByOng(ong);
    }

    // findOne
    public Ocorrencia findOne(Long id){
        Usuario usuario = usuarioService.getUsuarioLogado();
        Ocorrencia ocorrencia =  ocorrenciaRepository.findById(id).orElse(null);

        if(ocorrencia != null){
            if(usuarioService.verificaUsuario(ocorrencia.getPessoa().getUsuario())){
                return ocorrencia;
            }else if(usuario.getPapeis().contains(Papel.ADMIN)){
                return ocorrencia;
            }else {
                throw new DataIntegrityException("Ocorrencia de outra pessoa");
            }
        }else{
            throw new NotFoundException("Ocorrencia não encontrada");
        }
    }

    //buscar ocorrencia por id
    public Ocorrencia findById(Long id){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            return ocorrencia;
        }else{
            throw new NotFoundException("Ocorrência não encontrada");
        }
    }

    // delete ocorrencia por pessoa
    public void delete(Long id){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            if(usuarioService.verificaUsuario(ocorrencia.getPessoa().getUsuario())
                    && ocorrencia.getStatus().equals(Status.PENDENTE)){
                ocorrenciaRepository.delete(ocorrencia);
            }else{
                throw new NotFoundException("Ocorrencia com Status já atualizado!");
            }
        }else{
            throw new NotFoundException("Ocorrencia não encontrada!");
        }
    }

    // metodo atualizar status da ocorrencia por ong
    public Ocorrencia atualizarStatusDaOcorrenciaByOngCONFIRMADO(Long id){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            Usuario usuario = usuarioService.getUsuarioLogado();

            if(usuario.getPapeis().contains(Papel.USUARIO_ONG)){
                ocorrencia.setStatus(Status.CONFIRMADO);
                return ocorrenciaRepository.saveAndFlush(ocorrencia);
            }else{
                throw new DataIntegrityException("Erro! Certifique-se se as informações estão " +
                        "todas preenchidas ou se você pode realizar esta operação!");
            }

        }else {
            throw new NotFoundException("Ocorrencia não encontrada!");
        }
    }

    // metodo atualizar status da ocorrencia por ong
    public Ocorrencia atualizarStatusDaOcorrenciaByOngADOCAO(Long id){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            Usuario usuario = usuarioService.getUsuarioLogado();

            if(usuario.getPapeis().contains(Papel.USUARIO_ONG)){
                ocorrencia.setStatus(Status.ADOCAO);
                return ocorrenciaRepository.saveAndFlush(ocorrencia);
            }else{
                throw new DataIntegrityException("Erro! Certifique-se se as informações estão " +
                        "todas preenchidas ou se você pode realizar esta operação!");
            }

        }else {
            throw new NotFoundException("Ocorrencia não encontrada!");
        }
    }

    // metodo atualizar status da ocorrencia por ong
    public Ocorrencia atualizarStatusDaOcorrenciaByOngADOTADO(Long id){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            Usuario usuario = usuarioService.getUsuarioLogado();

            if(usuario.getPapeis().contains(Papel.USUARIO_ONG)){
                ocorrencia.setStatus(Status.ADOTADO);
                return ocorrenciaRepository.saveAndFlush(ocorrencia);
            }else{
                throw new DataIntegrityException("Erro! Certifique-se se as informações estão " +
                        "todas preenchidas ou se você pode realizar esta operação!");
            }

        }else {
            throw new NotFoundException("Ocorrencia não encontrada!");
        }
    }

    // metodo atualizar status da ocorrencia por ong
    public Ocorrencia atualizarStatusDaOcorrenciaByOngCANCELADO(Long id){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            Usuario usuario = usuarioService.getUsuarioLogado();

            if(usuario.getPapeis().contains(Papel.USUARIO_ONG)){
                ocorrencia.setStatus(Status.CANCELADO);
                return ocorrenciaRepository.saveAndFlush(ocorrencia);
            }else{
                throw new DataIntegrityException("Erro! Certifique-se se as informações estão " +
                        "todas preenchidas ou se você pode realizar esta operação!");
            }

        }else {
            throw new NotFoundException("Ocorrencia não encontrada!");
        }
    }

        // metodo atualizar status da ocorrencia por ong
    public Ocorrencia atualizarStatusDaOcorrenciaByOng(Long id, String statusSolicitacao){
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElse(null);
        if(ocorrencia != null){
            Usuario usuario = usuarioService.getUsuarioLogado();

            if(usuario.getPapeis().contains(Papel.USUARIO_ONG)){
                if(statusSolicitacao == "CONFIRMADO") {
                    ocorrencia.setStatus(Status.CONFIRMADO);
                }
                else if(statusSolicitacao == "ADOCAO") {
                    ocorrencia.setStatus(Status.ADOCAO);
                }
                else if(statusSolicitacao == "adotado") {
                    ocorrencia.setStatus(Status.ADOTADO);
                }
                else if (statusSolicitacao == "CANCELADO") {
                    ocorrencia.setStatus(Status.CANCELADO);
                }else{
                    throw new NotFoundException("Status inválido");
                }

                return ocorrenciaRepository.saveAndFlush(ocorrencia);
            }else{
                throw new DataIntegrityException("Erro! Certifique-se se as informações estão " +
                        "todas preenchidas ou se você pode realizar esta operação!");
            }

        }else {
            throw new NotFoundException("Ocorrencia não encontrada!");
        }

    }

}
