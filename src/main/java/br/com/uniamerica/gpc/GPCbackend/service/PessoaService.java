//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.controller.PessoaController;
import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import br.com.uniamerica.gpc.GPCbackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//------------------------------------------------
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void cadastrar(final Pessoa pessoa){

        Assert.isTrue(pessoa.getNome() != null, "ERRO COM NOME");
        Assert.isTrue(pessoa.getEmail() != null, "ERRO COM EMAIL");
        Assert.isTrue(pessoa.getTelefone() != null, "ERRO COM TELEFONE");
        String regexTel = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$";
        Assert.isTrue(pessoa.getTelefone().matches(regexTel), "TELEFONE INVALIDO");
        Assert.isTrue(pessoa.getRg() != null, "ERRO COM RG");
        String regexRG = "^\\d{9}$";
        Assert.isTrue(pessoa.getRg().matches(regexRG), "RG INVALIDO");
        Assert.isTrue(pessoa.getCpf() != null, "ERRO COM CPF");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(pessoa.getCpf().matches(regexCpf), "CPF INVALIDO");
        Assert.isTrue(pessoa.getEndereco() != null, "Algum Endereço já registrado");

        this.pessoaRepository.save(pessoa);

    }

}
