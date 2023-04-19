package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface MovimentacaoRepository extends JpaRepository <Movimentacao, Long> {
    @Query("from Movimentacao where Movimentacao.beneficiario.perfil.nome like :nome")
    public List<Movimentacao> findByMovimentacao(@Param("nome") String nome);

    @Query("from Movimentacao where Movimentacao.ativo.categoria.nomeCategoria like :nomeCategoria")
    public List<Movimentacao> findByAtivoCategoria(@Param("nomeCategoria") String nomeCategoria);

    @Query("from Movimentacao where Movimentacao.dataEmprestimo = :dataEmprestimo")
    public List<Movimentacao> findByDataEmprestimo(@Param("dataEmprestimo") LocalDate dataEmprestimo);
    @Query("from Movimentacao where Movimentacao.dataDevolucao = :dataEmprestimo")
    public List<Movimentacao> findByDataDevolucao(@Param("dataDevolucao") LocalDate dataDevolucao);

}