//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario;
import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import br.com.uniamerica.gpc.GPCbackend.repository.BeneficiarioRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import br.com.uniamerica.gpc.GPCbackend.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//------------------------------------------------
@Controller
@RequestMapping(value ="/beneficiarios")

public class BeneficiarioController {
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private BeneficiarioService beneficiarioService;
    @GetMapping(value="/id")
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
    final Beneficiario beneficiario = this.beneficiarioRepository.findById(id).orElse(null);
    return beneficiario == null ? ResponseEntity.badRequest().body("Nenhum beneficiário encontrado.") :
            ResponseEntity.ok(beneficiario);
    }

    @GetMapping(value ="/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.beneficiarioRepository.findAll());
    }

    @GetMapping("pdf/dataCriacao/{startDate}/{endDate}")
    public ResponseEntity<?> getByDataCriacaoPdf(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(beneficiarioRepository.findByDataCriacaoBetweenPdf(startDate,endDate));
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

    @PostMapping(value="/cadastrar")
    public ResponseEntity<?> cadastrar (@RequestBody final Beneficiario beneficiario){
        try{
            final Beneficiario beneficiarioBanco = this.beneficiarioService.cadastrar(beneficiario);
            return ResponseEntity.ok("Beneficiário cadastrado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /***
     *
     * @param id Caso exista uma movimentação no histórico do id do beneficiário, o beneficiário é suspenso.
     *
     * @return Caso não, ele é deletado.
     */
    @DeleteMapping
    public ResponseEntity<?> deletarBeneficiario(@RequestParam("id") final Long id){
        try{
            final Beneficiario beneficiarioBanco = this.beneficiarioRepository.findById(id).orElse(null);
            if(beneficiarioBanco == null){
                throw new RuntimeException("Beneficiário não encontrado.");
            }
            if(!this.movimentacaoRepository.findByBeneficiarioId(id).isEmpty()){
                beneficiarioBanco.setSuspenso(true);
                this.beneficiarioRepository.save(beneficiarioBanco);
                return ResponseEntity.ok("Beneficiário suspenso, pois existe histórico de movimentação.");
            }else{
                this.beneficiarioRepository.delete(beneficiarioBanco);
                return ResponseEntity.ok("Beneficiário deletado.");
            }
        }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?>atualizarBeneficiario(Long id, @RequestBody Beneficiario beneficiario){
        try{
            final Beneficiario beneficiarioBanco = this.beneficiarioRepository.findById(id).orElse(null);
            if(beneficiarioBanco == null || beneficiarioBanco.getId().equals(beneficiario.getId())){
                throw new RuntimeException("Não foi possível identificar o Beneiciário informado.");

            }final Beneficiario beneficiarioAtualizado = this.beneficiarioService.editar(beneficiario);

            return ResponseEntity.ok("Beneficiário atualizado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping(value="/cpf")
    public ResponseEntity<?>getByCpf(@RequestParam("cpf")final String cpf){
         final List<Beneficiario> beneficiarios = this.beneficiarioRepository.findByCpf(cpf);
        return beneficiarios == null ? ResponseEntity.badRequest().body("Nenhum beneficiário encontrado para o CPF") : ResponseEntity.ok(beneficiarios);
    }
}
