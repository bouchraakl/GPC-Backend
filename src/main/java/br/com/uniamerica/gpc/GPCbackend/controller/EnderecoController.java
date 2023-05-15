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
import org.springframework.web.bind.annotation.*;

//------------------------------------------------
@Controller
@RequestMapping("/enderecos")
public class EnderecoController {


    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam("id") Long id){
        final Endereco endereco = this.enderecoRepository.findById(id).orElse(null);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);

    }

    @GetMapping("/lista")
    public ResponseEntity<?> getByAll(){



        return ResponseEntity.ok(this.enderecoRepository.findAll());
    }

    @GetMapping("/cep") // GET ATRAVES DO CEP
    public ResponseEntity<?> getByCep(@RequestParam("cep")String cep){

        final Endereco endereco = this.enderecoRepository.findByCep(cep);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);
    }

    @GetMapping("/logradouro")// GET ATRAVES DO LOGRADOURO
    public ResponseEntity<?> getByLogradouro(@RequestParam("logradouro")String logradouro){

        final Endereco endereco = this.enderecoRepository.findByLogradouro(logradouro);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);
    }

    @GetMapping("/bairro")// GET ATRAVES DO BAIRRO
    public ResponseEntity<?> getByBairro(@RequestParam("bairro")String bairro){

        final Endereco endereco = this.enderecoRepository.findByBairro(bairro);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);
    }

    @GetMapping("/cidade")// GET ATRAVES DA CIDADE
    public ResponseEntity<?> getByCidade(@RequestParam("cidade")String cidade){

        final Endereco endereco = this.enderecoRepository.findByCidade(cidade);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);
    }

    @GetMapping("/uf") // GET ATRAVES DO UF
    public ResponseEntity<?> getByUf(@RequestParam("uf")String uf){

        final Endereco endereco = this.enderecoRepository.findByUf(uf);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);
    }

    @GetMapping("/pais") //GET ATRAVES DO PAIS
    public ResponseEntity<?> getByPais(@RequestParam("pais")String pais){

        final Endereco endereco = this.enderecoRepository.findByPais(pais);
        return endereco == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(endereco);
    }


    @PostMapping
    public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco){

        try{
            final Endereco enderecoBanco = this.enderecoService.cadastrar(endereco);

            return  ResponseEntity.ok("Endereço cadastrado com sucesso");

        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar endereco.");
        }
    }

    @PutMapping
    public ResponseEntity<?> editarEndereco(@RequestParam("id") Long id, @RequestBody Endereco endereco){

        try{
            final Endereco enderecoBanco = this.enderecoService.editar(endereco);
            return ResponseEntity.ok("Endereço atualizado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao editar endereco.");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletarEndereco(@RequestParam("id") Long id, @RequestBody Endereco endereco){


        try{
            final Endereco enderecoBanco = this.enderecoService.deletar(endereco);
            return ResponseEntity.ok("Endereço deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao deletar endereco.");
        }
    }
}
