//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.controller.CategoriaController;
import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Categoria;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.CategoriaRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Console;
import java.util.List;
import java.util.Objects;

//------------------------------------------------
@Service
public class CategoriaService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AtivoRepository ativoRepository;


    @Transactional
    public Categoria cadastrar(Categoria categoria){

        Assert.notNull(categoria.getNomeCategoria(), "Nome da categoria não informado");
        Assert.notNull(categoria.getMinimoAmarelo(), "Número minimo de itens para dar alerta de estoque não informado");
        Assert.notNull(categoria.getMaximoAmarelo(), "Número maximo de itens para dar alerta de estoque não informado");

        return this.categoriaRepository.save(categoria);
    }

    @Transactional
    public ResponseEntity<?> editar(Categoria categoria){

        final Categoria categoriaBanco = this.categoriaRepository.findById(categoria.getId()).orElse(null);
        Assert.notNull(categoriaBanco, "Categoria inexistente!");

        Assert.isTrue(categoriaBanco.getId().equals(categoria.getId()), "Categoria informada não é a mesmo que a categoria a ser atualizada");


//        List<Movimentacao> nomeCategoriaBanco = this.movimentacaoRepository.findByCategoria(categoria.getNomeCategoria());
//
//        if (nomeCategoriaBanco != null){
//        if (categoria.getNomeCategoria() != null){
//            return ResponseEntity.badRequest().body("Não é possivel editar o nome da categoria enquanto estiver movimentações atreladas a ela");
//
//        }
//        }

        this.categoriaRepository.save(categoria);
        return ResponseEntity.ok().body("Editado com sucesso!");

    }

    @Transactional
    public void deletar(Long id){

        final Categoria categoria = this.categoriaRepository.findById(id).orElse(null);
        Assert.notNull(categoria, "Categoria não encontrado!");

        if(!this.movimentacaoRepository.findByAtivoCategoriaId(id).isEmpty()){
            categoria.setSuspenso(true);
            this.categoriaRepository.save(categoria);
        }else{
            this.categoriaRepository.deleteById(id);
        }
    }



}
