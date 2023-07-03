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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//------------------------------------------------
@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaService pessoaService;

    /**
     * Metodo GET para encontrar uma {@link Pessoa} pelo ID
     * @param id
     * @return Se a Pessoa for encontrada, retorna uma resposta HTTP 200 com a Pessoa no Body.
     * Se a Pessoa não for encontrada, retorna uma resposta HTTP 400 com uma mensagem de erro no Body.
     */
    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
        return pessoa == null ? ResponseEntity.badRequest().body("Pessoa não encontrada") : ResponseEntity.ok(pessoa);
    }

    /**
     * Método GET para retornar todas as Pessoas
     * @return Todos as Pessoas
     */
    @GetMapping("/todos")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.pessoaRepository.findAll());
    }


    @GetMapping("/cpf")
    public ResponseEntity<?> findByCPF(@RequestParam("cpf") String cpf){

        return ResponseEntity.ok(this.pessoaRepository.findByCpf(cpf));
    }

    /**
     * Método GET para retornar as Pessoas em que a flag isSuspenso é false, ou seja, que estão ativas
     * @return Todas as Pessoas ativas
     */
    @GetMapping("/ativos")
    public ResponseEntity<?> findByAtivo(){
        return ResponseEntity.ok(this.pessoaRepository.findByAtivo());
    }

    /**
     * Método POST para Cadastrar uma {@link Pessoa} no repoitório
     * @param pessoa do tipo {@link Pessoa}  para ser cadastrado no repositório
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrarPessoa (@RequestBody @Validated Pessoa pessoa){
        try{
            this.pessoaService.cadastrar(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    /**
     * Método PUT que edita a pessoa correspondente do ID passado como parametro usando a {@link Pessoa} passada no Body
     * @param id da Pessoa a ser atualizada
     * @param pessoa do tipo {@link Pessoa} com os dados atualizados para ser persistida no repositório
     * @return
     */
    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody Pessoa pessoa
    ){
        try {
            final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
            this.pessoaService.editar(pessoa);
            return ResponseEntity.ok("Pessoa atualizada com sucesso!");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }


    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        try{
            final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
            this.pessoaService.delete(pessoaBanco);
            return ResponseEntity.ok("Pessoa desativada");
        }catch(Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }
}
