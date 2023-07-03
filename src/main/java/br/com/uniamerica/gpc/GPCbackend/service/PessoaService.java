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
import java.util.List;

//------------------------------------------------
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void cadastrar(final Pessoa pessoa){
        final List<Pessoa> pessoaByCpf = this.pessoaRepository.findByCpf(pessoa.getCpf());
        Assert.isTrue(pessoaByCpf.isEmpty(), String.format("Pessoa com CPF [ %s ] já existe!", pessoa.getCpf()));
        final List<Pessoa> pessoaByRg = this.pessoaRepository.findByRg(pessoa.getRg());
        Assert.isTrue(pessoaByRg.isEmpty(), String.format("Pessoa com RG [ %s ] já existe!", pessoa.getRg()));
        this.pessoaRepository.save(pessoa);
    }

    public void editar(final Pessoa pessoa){
        final List<Pessoa> pessoaByCpf = this.pessoaRepository.findByCpf(pessoa.getCpf());
        Assert.isTrue(pessoaByCpf.get(0).getId().equals(pessoa.getId()), "Condutor informado não é o mesmo que o condutor a ser atualizado");
        final List<Pessoa> pessoaByRg = this.pessoaRepository.findByRg(pessoa.getRg());
        Assert.isTrue(pessoaByRg.get(0).getId().equals(pessoa.getId()), "Condutor informado não é o mesmo que o condutor a ser atualizado");

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
