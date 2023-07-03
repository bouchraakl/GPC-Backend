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
        this.pessoaRepository.save(pessoa);
    }

    public void editar(final Pessoa pessoa){
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
