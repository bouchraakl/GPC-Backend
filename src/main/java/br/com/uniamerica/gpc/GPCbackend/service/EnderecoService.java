//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.entity.Endereco;
import br.com.uniamerica.gpc.GPCbackend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//------------------------------------------------
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco cadastrar(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco editar(Endereco endereco){


        Assert.hasText(endereco.getCep(), "Não informado o CEP corretamente");
        Assert.notNull(endereco.getCep(), "Não informado o CEP");

        Assert.hasText(endereco.getLogradouro(), "Não informado LOGRADOURO corretamente");
        Assert.notNull(endereco.getLogradouro(), "Não informado LOGRADOURO");

        Assert.hasText(endereco.getBairro(), "NÃO informado BAIRRO corretamente");
        Assert.notNull(endereco.getBairro(),"NÃO informado BAIRRO");

        Assert.hasText(endereco.getCidade(), "NÃO informado CIDADE corretamente");
        Assert.notNull(endereco.getCidade(), "NÃO Informado CIDADE");

        Assert.hasText(endereco.getUf(), "NÃO informado UF corretamente");
        Assert.notNull(endereco.getUf(), "NÃO informado UF");

        Assert.hasText(endereco.getPais(), "NÃO informado PAIS corretamente");
        Assert.notNull(endereco.getPais(), "NÃO informado PAIS");

        Assert.notNull(endereco.getId(), "ID não informado");

        Assert.isTrue(enderecoRepository.existsById(endereco.getId()), "Endereço não é compativel");

        Assert.isTrue(!enderecoRepository.equals(endereco.getId()), "ID não compativel com o ID do banco");


        return enderecoRepository.save(endereco);
    }


    @Transactional
    public Endereco deletar(Endereco endereco){


        Assert.isTrue(!enderecoRepository.equals(endereco.getId()), "ID não compativel com o ID do banco");
        Assert.isTrue(enderecoRepository.existsById(endereco.getId()), "ID não existente no banco de dados");

        endereco.setSuspenso(true);

        return enderecoRepository.save(endereco);
    }
}
