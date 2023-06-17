//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import br.com.uniamerica.gpc.GPCbackend.entity.Usuario;
import br.com.uniamerica.gpc.GPCbackend.repository.PessoaRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//------------------------------------------------
@Service
public class UsuarioService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrar(final Usuario usuario){

        Assert.isTrue(usuario.getPerfil().getCpf() != null, "CPF NÃO CADASTRADO");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(usuario.getPerfil().getCpf().matches(regexCpf), "CPF INVALIDO");
        Assert.isTrue(usuario.getLogin() != null, "LOGIN JÁ CADASTRADO");
        Assert.isTrue(usuario.getPassword() != null, "SENHA JÁ CADASTRADA");


        this.usuarioRepository.save(usuario);

    }

    public void editar(final Pessoa pessoa){
        final Pessoa pessoaBanco = this.pessoaRepository.findById(pessoa.getId()).orElse(null);

        Assert.isTrue(pessoaBanco != null || !pessoaBanco.getId().equals(pessoa.getId()), "Registro não identificado!");
        Assert.hasText(pessoa.getNome(), "ERRO COM NOME");
        Assert.hasText(pessoa.getEmail(), "ERRO COM EMAIL");
        String regexTel = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$";
        Assert.isTrue(pessoa.getTelefone().matches(regexTel), "TELEFONE INVALIDO");
        Assert.hasText(pessoa.getTelefone(), "ERRO COM TELEFONE");
        String regexRG = "^\\d{9}$";
        Assert.isTrue(pessoa.getRg().matches(regexRG), "RG INVALIDO");
        Assert.hasText(pessoa.getRg(), "ERRO COM RG");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(pessoa.getCpf().matches(regexCpf), "CPF INVALIDO");
        Assert.hasText(pessoa.getCpf(), "ERRO COM CPF");

        this.pessoaRepository.save(pessoa);

    }

    public void delete(final Usuario usuario){

        final Usuario usuarioBanco = this.usuarioRepository.findById(usuario.getId()).orElse(null);

        if(usuarioBanco == null){
            throw new RuntimeException("Registro não encontrado");
        }
        if(!this.usuarioRepository.findByPerfilId(usuarioBanco.getId()).isEmpty()){
            usuarioBanco.setSuspenso(false);
        }else{
            this.usuarioRepository.delete(usuarioBanco);
        }

    }

}
