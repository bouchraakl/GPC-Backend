//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Endereco;
import br.com.uniamerica.gpc.GPCbackend.repository.EnderecoRepository;
import br.com.uniamerica.gpc.GPCbackend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//------------------------------------------------
@Controller
@RequestMapping("/enderecos")
public class EnderecoController {


    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecoService enderecoService;



    @GetMapping
    public ResponseEntity<?> getByCep(@RequestParam("cep")String cep){

        final Endereco endereco = this.enderecoRepository.findByCep(cep);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

    @GetMapping("/logradouro")
    public ResponseEntity<?> getByLogradouro(@RequestParam("logradouro")String logradouro){

        final Endereco endereco = this.enderecoRepository.findByLogradouro(logradouro);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

    @GetMapping("/bairro")
    public ResponseEntity<?> getByBairro(@RequestParam("bairro")String bairro){

        final Endereco endereco = this.enderecoRepository.findByBairro(bairro);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

    @GetMapping("/cidade")
    public ResponseEntity<?> getByCidade(@RequestParam("cidade")String cidade){

        final Endereco endereco = this.enderecoRepository.findByCidade(cidade);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

    @GetMapping("/uf")
    public ResponseEntity<?> getByUf(@RequestParam("uf")String uf){

        final Endereco endereco = this.enderecoRepository.findByUf(uf);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

    @GetMapping("/uf")
    public ResponseEntity<?> getByPais(@RequestParam("pais")String pais){

        final Endereco endereco = this.enderecoRepository.findByPais(pais);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

}
