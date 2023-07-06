//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Condicao;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import br.com.uniamerica.gpc.GPCbackend.entity.Status;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import br.com.uniamerica.gpc.GPCbackend.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/ativos")
public class AtivoController {

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private AtivoService ativoService;

    /**
     * Este método manipula as solicitações GET para recuperar um objeto Ativo pelo seu ID.
     *
     * @param id O ID do Ativo a ser recuperado.
     * @return Se o Ativo for encontrado, retorna uma resposta HTTP 200 com o Ativo como body.
     * Se o Ativo não for encontrado, retorna uma resposta HTTP 400 com uma mensagem de erro no body.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdRequest(@PathVariable("id") Long id) {
        final Ativo ativo = ativoRepository.findById(id).orElse(null);
        return ativo == null ? ResponseEntity.badRequest().body("ID não encontrado") : ResponseEntity.ok(ativo);
    }

    /**
     * Manipula solicitações GET para "/listar" e recupera uma lista de todos os objetos Ativo do repositório.
     *
     * @return uma ResponseEntity contendo a lista de objetos Ativo,
     * ou uma resposta badRequest se a lista estiver vazia
     */
    @GetMapping
    public ResponseEntity<Page<Ativo>> getAllRequest(Pageable pageable) {
        return ResponseEntity.ok(this.ativoService.listAll(pageable));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.ativoRepository.findAll());
    }

    @GetMapping("pdf/dataCriacao/{startDate}/{endDate}")
    public ResponseEntity<?> getByDataCriacaoPdf(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(ativoRepository.findByDataCriacaoBetweenPdf(startDate,endDate));
    }


    /**
     * Manipula solicitações GET para "/condicao" e recupera uma lista de objetos Ativo com a condição especificada.
     *
     * @param condicao a condição dos objetos Ativo a serem recuperados
     * @return uma ResponseEntity contendo a lista de objetos Ativo filtrados pela condição,
     * ou uma resposta notFound se a lista estiver vazia
     */
    @GetMapping("/condicao")
    public ResponseEntity<?> getByCondicao(@RequestParam("condicao") Condicao condicao) {
        List<Ativo> ativos = this.ativoRepository.findByCondicao(condicao);
        return ativos.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(ativos);
    }

    /**
     * Manipula solicitações GET para "/status" e recupera uma lista de objetos Ativo com o status especificado.
     *
     * @param status o status dos objetos Ativo a serem recuperados
     * @return uma ResponseEntity contendo a lista de objetos Ativo filtrados pelo status,
     * ou uma resposta notFound se a lista estiver vazia
     */
    @GetMapping("/status")
    public ResponseEntity<?> getByStatus(@RequestParam Status status) {
        List<Ativo> ativos = this.ativoRepository.findByStatus(status);
        return ativos.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(ativos);
    }

    /**
     * Manipula solicitações GET para "/idPatrimonio" e recupera um objeto Ativo com o idPatrimonio especificado.
     *
     * @param idPatrimonio o idPatrimonio do objeto Ativo a ser recuperado
     * @return uma ResponseEntity contendo o objeto Ativo com o idPatrimonio especificado,
     * ou uma resposta badRequest se o idPatrimonio for inválido
     */
    @GetMapping("/idPatrimonio")
    public ResponseEntity<?> getByIdPatrimonio(@RequestParam("idPatrimonio") String idPatrimonio) {

        final Ativo ativo = this.ativoRepository.findByIdPatrimonio(idPatrimonio);

        if (ativo.getIdPatrimonio() == null) {
            return ResponseEntity.badRequest().body("idPatrimonio inválido");
        }

        return ResponseEntity.ok(ativo);
    }

    @GetMapping("/idcategoria")
    public ResponseEntity<?> getByIdCategoria(@RequestParam("id") Long id) {

        final List<Ativo> ativo = this.ativoRepository.findByCategoriaId(id);


        return ativo.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(ativo);

    }


    /**
     * Manipula solicitações GET para "/categoria" e recupera uma lista de objetos Ativo com o
     * nome da categoria especificada.
     *
     * @param nomeCategoria o nome da categoria dos objetos Ativo a serem recuperados
     * @return uma ResponseEntity contendo a lista de objetos Ativo com o nome da categoria especificada
     */
    @GetMapping("/nomeCategoria")
    public ResponseEntity<?> getByNomeCategoria(@RequestParam("nomeCategoria") String nomeCategoria) {
        try {

            final List<Ativo> ativosByNomeCategoria = this.ativoRepository.findByNomeCategoria(nomeCategoria);


            if (ativosByNomeCategoria.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(ativosByNomeCategoria);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body("Nome da categoria inválido.");
        }
    }

    /**
     * Endpoint para criar um novo ativo.
     *
     * @param ativo O objeto de ativo a ser criado.
     * @return Uma resposta de sucesso com uma mensagem ou uma resposta de erro com uma mensagem.
     */
    @PostMapping
    public ResponseEntity<String> cadastrarAtivo(@RequestBody @Validated Ativo ativo) {
        try {
            this.ativoService.validarCadastroAtivo(ativo);
            return ResponseEntity.ok("Ativo cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Atualiza um Ativo com o ID especificado.
     *
     * @param ativo O objeto Ativo atualizado.
     * @return Um ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @PutMapping
    public ResponseEntity<?> editarAtivo(
            @RequestBody @Validated Ativo ativo
    ) {
        try {
            this.ativoService.validarUpdateAtivo(ativo);
            return ResponseEntity.status(HttpStatus.OK).body("Ativo modificado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Exclui um Ativo com o ID especificado.
     *
     * @param id O ID do Ativo a ser excluído.
     * @return Um ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
//    @DeleteMapping
//    public ResponseEntity<?> excluirAtivo(@RequestParam("id") Long id) {
//        try {
//            this.ativoService.validarDeleteAtivo(id);
//            return ResponseEntity.ok("Ativo excluído com sucesso.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (EmptyResultDataAccessException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ativo não encontrado.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erro ao excluir ativo: " + e.getMessage());
//        }
//    }
    @DeleteMapping
    public ResponseEntity<?> deletar(
            @RequestParam("id") final Long id
    ) {
        try {
            this.ativoService.validarDeleteAtivo(id);
            return ResponseEntity.ok(String.format("Ativo [ %s ] desativado!", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
