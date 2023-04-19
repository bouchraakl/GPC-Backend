package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Categoria;
import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtivoRepository extends JpaRepository <Ativo, Long> {
    @Query("from Ativo where idPatrimonio like :idPatrimonio")
    public List<Ativo> findByAtivo(@Param("idPatrimonio") final String idPatrimonio);

    /* Query para buscar o ativo atraves do nome da categoria*/
    @Query("select ativo from Ativo ativo join ativo.categoria categoria where categoria.nomeCategoria = :nomeCategoria")
    public List<Ativo> findByCategoria(@Param("nomeCategoria") String nomeCategoria);

}