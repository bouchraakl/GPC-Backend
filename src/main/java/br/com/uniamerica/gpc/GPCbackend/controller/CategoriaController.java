//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.controller;

//------------------Imports----------------------


import br.com.uniamerica.gpc.GPCbackend.entity.Categoria;
import br.com.uniamerica.gpc.GPCbackend.entity.Endereco;
import br.com.uniamerica.gpc.GPCbackend.repository.CategoriaRepository;
import br.com.uniamerica.gpc.GPCbackend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//------------------------------------------------
@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<?> getById(@RequestParam("id") Long id){
        final Categoria categoria = this.categoriaRepository.findById(id).orElse(null);
        return categoria == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(categoria);

    }

    @GetMapping("/ativo")
    public ResponseEntity<?> getByAtivos(){
        final List<Categoria> listaAtivos = this.categoriaRepository.findByAtivo();
        return listaAtivos == null ? ResponseEntity.badRequest().body("nenhum endereço encontrado") : ResponseEntity.ok(listaAtivos);

    }



}
