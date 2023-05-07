//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Condicao;
import br.com.uniamerica.gpc.GPCbackend.entity.Status;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//------------------------------------------------

@Controller
@RequestMapping(value = "/ativos")
public class AtivoController {

    @Autowired
    private AtivoRepository ativoRepository;

    /**
     * Este método manipula as solicitações GET para recuperar um objeto Ativo pelo seu ID.
     *
     * @param id O ID do Ativo a ser recuperado.
     * @return Se o Ativo for encontrado, retorna uma resposta HTTP 200 com o Ativo como body.
     * Se o Ativo não for encontrado, retorna uma resposta HTTP 400 com uma mensagem de erro no body.
     */
    @GetMapping
    public ResponseEntity<?> getByIdRequest(@RequestParam("id") final Long id) {
        final Ativo ativo = this.ativoRepository.findById(id).orElse(null);
        return ativo == null
                ? ResponseEntity.badRequest().body("ID não encontrado")
                : ResponseEntity.ok(ativo);
    }

    /**
     * Manipula solicitações GET para "/listar" e recupera uma lista de todos os objetos Ativo do repositório.
     *
     * @return uma ResponseEntity contendo a lista de objetos Ativo,
     * ou uma resposta badRequest se a lista estiver vazia
     */
    @GetMapping("/listar")
    public ResponseEntity<?> getAllRequest() {
        return ResponseEntity.ok(this.ativoRepository.findAll());
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
    public ResponseEntity<?> findByIdPatrimonio(@RequestParam("idPatrimonio") String idPatrimonio) {

        final Ativo ativo = this.ativoRepository.findByIdPatrimonio(idPatrimonio);

        if (ativo.getIdPatrimonio() == null) {
            return ResponseEntity.badRequest().body("idPatrimonio inválido");
        }

        return ResponseEntity.ok(ativo);
    }

    /**
     * Manipula solicitações GET para "/categoria" e recupera uma lista de objetos Ativo com o
     * nome da categoria especificada.
     *
     * @param nomeCategoria o nome da categoria dos objetos Ativo a serem recuperados
     * @return uma ResponseEntity contendo a lista de objetos Ativo com o nome da categoria especificada
     */
    public ResponseEntity<?> findByNomeCategoria(@RequestParam("nomeCategoria") String nomeCategoria) {
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





}
