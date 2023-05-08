//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario;
import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import br.com.uniamerica.gpc.GPCbackend.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//------------------------------------------------
@Controller
@RequestMapping(value ="/beneficiarios")
public class BeneficiarioController {
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
    final Beneficiario beneficiario = this.beneficiarioRepository.findById(id).orElse(null);
    return beneficiario == null ? ResponseEntity.badRequest().body("Nenhum beneficiário encontrado.") :
            ResponseEntity.ok(beneficiario);
    }

    @GetMapping(value ="/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.beneficiarioRepository.findAll());
    }


    @GetMapping(value = "/nomes") //O Hibernate alertava caminho ambíguo caso eu deixasse um caminho vazio aqui.
    public ResponseEntity<?> findByNome(@RequestParam("nome")String nome) {
        try {
            final List<Beneficiario> beneficiariosNome = this.beneficiarioRepository.findByNome(nome);
            if (beneficiariosNome.isEmpty()) {
                return ResponseEntity.badRequest().body("Nenhum beneficiário encontrado com o nome inserido.");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(nome);
    }



    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Beneficiario beneficiario){
        try{
            this.beneficiarioRepository.save(beneficiario);
            return ResponseEntity.ok("Beneficiário cadastrado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
