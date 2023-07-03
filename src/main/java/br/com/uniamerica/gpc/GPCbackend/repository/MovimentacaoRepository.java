package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jean Moschen
 * */

@Repository
public interface MovimentacaoRepository extends JpaRepository <Movimentacao, Long> {
    /**
     * @param beneficiarioNome ID do Beneficiario {@link br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario}, para filtrar as Movimentações {@link Movimentacao}
     * @return Lista de movimentações que contêm o beneficiário informado
     */
    @Query("from Movimentacao where beneficiario.perfil.nome like :beneficiarioNome")
    public List<Movimentacao> findByBeneficiarioNome(@Param("beneficiarioNome") String beneficiarioNome);

    /**
     * @param id ID da Categoria {@link br.com.uniamerica.gpc.GPCbackend.entity.Categoria}, para filtrar as Movimentações {@link Movimentacao}
     * @return Lista de Movimentações que contêm a categoria informada
     */
    @Query("from Movimentacao where ativo.categoria.id = :id")
    public List<Movimentacao> findByAtivoCategoriaId(@Param("id") Long id);

    @Query("from Movimentacao where ativo.categoria = :categoria")
    public List<Movimentacao> findByCategoria(@Param("categoria") String categoria);


    @Query("from Movimentacao where ativo.id = :ativoId")
    public List<Movimentacao> findByAtivoId(@Param("ativoId") Long ativoId);

    @Query("from Movimentacao where ativo.idPatrimonio = :idPatrimonio")
    public List<Movimentacao> findByAtivoPatrimonio(@Param("idPatrimonio") Long idPatrimonio);

    @Query("from Movimentacao where beneficiario.id = :beneficiarioId")
    public List<Movimentacao> findByBeneficiarioId(@Param("beneficiarioId") Long beneficiarioId);

    /**
     * @param dataEmprestimo Data de Incio do empréstimo, para filtrar as Movimentações
     * @return Lista de Movimentações que foram iniciadas na data informada
     */
    @Query("from Movimentacao where dataEmprestimo = :dataEmprestimo")
    public List<Movimentacao> findByDataEmprestimo(@Param("dataEmprestimo") LocalDate dataEmprestimo);

    /**
     * @param dataDevolucao Data de Devolução do empréstimo, para filtrar as Movimentações
     * @return Lista de Movimentações que foram encerradas na data informada ou estão previstas para serem encerradas.
     */
    @Query("from Movimentacao where dataDevolucao = :dataDevolucao")
    public List<Movimentacao> findByDataDevolucao(@Param("dataDevolucao") LocalDate dataDevolucao);

    /**
     * @return Todas as movimentações em que o Ativo ainda não foi devolido, portanto, todas as movimentações em aberto
     */
    @Query("from Movimentacao  where isDevolvido = true")
    public List<Movimentacao> findAllAbertas();

    @Query("SELECT m FROM Movimentacao m " +
            "WHERE (:dataEntrada IS NULL OR m.dataEmprestimo = :dataEntrada) " +
            "AND (:dataDevolucao IS NULL OR m.dataDevolucao = :dataDevolucao) " +
            "AND (:beneficiarioId IS NULL OR m.beneficiario.id = :beneficiarioId) " +
            "AND (:categoriaId IS NULL OR m.ativo.categoria.id = :categoriaId) " +
            "AND (:ativoPatrimonio IS NULL OR m.ativo.idPatrimonio = :ativoPatrimonio) " +
            "AND (:ativoId IS NULL OR m.ativo.id = :ativoId)")
    List<Movimentacao> filtrarMovimentacoes(
            @Param("dataEntrada") LocalDate dataEntrada,
            @Param("dataDevolucao") LocalDate dataDevolucao,
            @Param("beneficiarioId") Long beneficiarioId,
            @Param("categoriaId") Long categoriaId,
            @Param("ativoId") Long ativoId
    );
    @Query("SELECT m FROM Movimentacao m " +
            "WHERE m.dataEmprestimo >= :dataEntrada " +
            "AND m.dataDevolucao <= :dataDevolucao")
    List<Movimentacao> findMovementsBetweenDates(
            @Param("dataEntrada") LocalDate dataEntrada,
            @Param("dataDevolucao") LocalDate dataDevolucao);

}