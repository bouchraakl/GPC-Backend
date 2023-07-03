//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import br.com.uniamerica.gpc.GPCbackend.repository.PessoaRepository;
import br.com.uniamerica.gpc.GPCbackend.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//------------------------------------------------
@Controller
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);

        return pessoa == null ? ResponseEntity.badRequest().body("Pessoa não encontrada") : ResponseEntity.ok(pessoa);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){

        return ResponseEntity.ok(this.pessoaRepository.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getByIdRequest(@PathVariable("cpf") String cpf) {
        final Pessoa pessoa = pessoaRepository.findByCpf(cpf);
        return pessoa == null ? ResponseEntity.badRequest().body("cpf não encontrado") : ResponseEntity.ok(pessoa);
    }


    @GetMapping("/ativos")
    public ResponseEntity<?> findByAtivo(){

        return ResponseEntity.ok(this.pessoaRepository.findByAtivo());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa (@RequestBody final Pessoa pessoa){
        try{
            this.pessoaService.cadastrar(pessoa);
            return ResponseEntity.ok("Cadastrado com sucesso");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody Pessoa pessoa
    ){
        try {
            final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

            this.pessoaService.editar(pessoa);
            return ResponseEntity.ok("Dados atualizados");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    public ResponseEntity<?> excluirPessoa(@RequestParam("id") final Long id){
        try{
            final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

            this.pessoaService.delete(pessoaBanco);
            return ResponseEntity.ok("Pessoa excluida");
        }catch(Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }
}
