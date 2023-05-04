//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//------------------------------------------------
@Controller
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }
    @GetMapping
    public ResponseEntity<?> getById(@RequestParam("id") Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao == null ? ResponseEntity.badRequest().body("Nenhuma movimentação encontrada") : ResponseEntity.ok(movimentacao);
    }

}

