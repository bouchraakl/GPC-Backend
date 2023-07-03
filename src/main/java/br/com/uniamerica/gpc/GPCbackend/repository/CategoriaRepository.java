package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Categoria;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
//    @Query("from Categoria where nomeCategoria like :nomeCategoria")
//    public List<Categoria> findByNome(@Param("nomeCategoria") final String nomeCategoria);

    @Query("SELECT c FROM Categoria c WHERE c.nomeCategoria like :nomeCategoria")
    public Categoria findByNome(@Param("nomeCategoria") final String nome);

    @Query("from Categoria where isSuspenso = false")
    public List<Categoria> findByAtivo();

    @Query("select listaEspera from Categoria where nomeCategoria like :nomeCategoria")
    public List<Categoria> findByListaEspera(@Param("nomeCategoria") final String nomeCategoria);

    @Query("SELECT c FROM Categoria c WHERE DATE(c.dataCriacao) BETWEEN :startDate AND :endDate")
    List<Categoria> findByDataCriacaoBetweenPdf(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
