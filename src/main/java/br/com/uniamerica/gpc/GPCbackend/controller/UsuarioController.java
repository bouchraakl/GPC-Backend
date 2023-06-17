//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import br.com.uniamerica.gpc.GPCbackend.entity.Usuario;
import br.com.uniamerica.gpc.GPCbackend.repository.UsuarioRepository;
import br.com.uniamerica.gpc.GPCbackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//------------------------------------------------

@Controller
@RequestMapping(value = "/user")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        return usuario == null ? ResponseEntity.badRequest().body("Usuario não encontrada") : ResponseEntity.ok(usuario);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){

        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> findByAtivo(){

        return ResponseEntity.ok(this.usuarioRepository.findByAtivo());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa (@RequestParam final Usuario usuario){
        try{
            this.usuarioService.cadastrar(usuario);
            return ResponseEntity.ok("Cadastrado com sucesso");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody Usuario usuario
    ){
        try {
            final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);

            this.usuarioService.editar(usuario.getPerfil());
            return ResponseEntity.ok("Dados atualizados");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    public ResponseEntity<?> excluirUser(@RequestParam("id") final Long id){
        try{
            final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);

            this.usuarioService.delete(usuarioBanco);
            return ResponseEntity.ok("Usuario deletado");
        }catch(Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

}
