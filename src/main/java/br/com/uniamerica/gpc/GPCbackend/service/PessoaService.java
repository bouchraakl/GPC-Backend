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

import java.time.LocalDateTime;

//------------------------------------------------
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void cadastrar(final Pessoa pessoa){

        pessoa.setDataCriacao(LocalDateTime.now());

        Assert.isTrue(pessoa.getNome() != null, "ERRO COM NOME");
        Assert.hasText(pessoa.getNome(), "ERRO COM NOME");
        Assert.isTrue(pessoa.getEmail() != null, "ERRO COM EMAIL");
        String regexEmail = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Assert.isTrue(pessoa.getEmail().matches(regexEmail), "EMAIL INVALIDO");
        Assert.hasText(pessoa.getEmail(), "ERRO COM EMAIL");
        Assert.isTrue(pessoa.getTelefone() != null, "ERRO COM TELEFONE");
        String regexTel = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$";
        Assert.isTrue(pessoa.getTelefone().matches(regexTel), "TELEFONE INVALIDO");
        Assert.hasText(pessoa.getTelefone(), "ERRO COM TELEFONE");
        Assert.isTrue(pessoa.getRg() != null, "ERRO COM RG");
        String regexRG = "^\\d{9}$";
        Assert.isTrue(pessoa.getRg().matches(regexRG), "RG INVALIDO");
        Assert.hasText(pessoa.getRg(), "ERRO COM RG");
        Assert.isTrue(pessoa.getCpf() != null, "ERRO COM CPF");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(pessoa.getCpf().matches(regexCpf), "CPF INVALIDO");
        Assert.hasText(pessoa.getCpf(), "ERRO COM CPF");
        Assert.isTrue(pessoa.getEndereco() != null, "ERRO COM ENDEREÇO");

        if (pessoaRepository.existsByEmail(pessoa.getEmail()) || pessoaRepository.existsByCpf(pessoa.getCpf()) == true){
        this.pessoaRepository.save(pessoa);
        }

    }

    public void editar(final Pessoa pessoa){
        final Pessoa pessoaBanco = this.pessoaRepository.findById(pessoa.getId()).orElse(null);

        Assert.isTrue(pessoaBanco != null || !pessoaBanco.getId().equals(pessoa.getId()), "Registro não identificado!");
        Assert.hasText(pessoa.getNome(), "ERRO COM NOME");
        Assert.hasText(pessoa.getEmail(), "ERRO COM EMAIL");
        String regexEmail = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Assert.isTrue(pessoa.getEmail().matches(regexEmail), "EMAIL INVALIDO");
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

    public void delete(final Pessoa pessoa){

        final Pessoa pessoaBanco = this.pessoaRepository.findById(pessoa.getId()).orElse(null);

        if(pessoaBanco == null){
            throw new RuntimeException("Registro não encontrado");
        }
        if(!this.pessoaRepository.findByEnderecoId(pessoaBanco.getId()).isEmpty()){
            pessoaBanco.setSuspenso(false);
        }else{
            this.pessoaRepository.delete(pessoaBanco);
        }

    }

}
