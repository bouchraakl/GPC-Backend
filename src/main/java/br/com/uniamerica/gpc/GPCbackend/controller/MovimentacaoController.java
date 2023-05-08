//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import br.com.uniamerica.gpc.GPCbackend.service.MovimentacaoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//------------------------------------------------
@Controller
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }
    @GetMapping
    public ResponseEntity<?> getById(@RequestParam("id") Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao == null ? ResponseEntity.badRequest().body("Nenhuma movimentação encontrada") : ResponseEntity.ok(movimentacao);
    }

    @PostMapping
    public ResponseEntity<?> novaMovimentacao(@RequestBody Movimentacao movimentacao){
        try {
            final Movimentacao movimentacaoBanco = this.movimentacaoService.novaMovimentacao(movimentacao);
            return ResponseEntity.ok(String.format("Movimentação [ %s ] iniciada com sucesso", movimentacaoBanco.getId()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

