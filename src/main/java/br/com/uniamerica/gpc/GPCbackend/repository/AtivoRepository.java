//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.repository;

//------------------Imports----------------------

import br.com.uniamerica.gpc.GPCbackend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//------------------------------------------------

/**
 * @author Bouchra Akl
 */

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {

    /**
     * @param condicao a ser utilizada como critério de busca
     * @return uma lista de objetos Ativo que satisfazem à condição informada
     */
    @Query("SELECT a FROM Ativo a WHERE a.condicao = :condicao")
    List<Ativo> findByCondicao(@Param("condicao") Condicao condicao);


    /**
     * @param status o status a ser utilizado como critério de busca
     * @return uma lista de objetos Ativo que possuem o status informado
     */
    @Query("SELECT a FROM Ativo a WHERE a.status = :status")
    public List<Ativo> findByStatus(@Param("status") final Status status);


    /**
     * @param idPatrimonio o idPatrimonio do objeto Ativo a ser retornado
     * @return o objeto Ativo com o idPatrimonio especificado
     */
    @Query("SELECT a FROM Ativo a WHERE a.idPatrimonio like :idPatrimonio")
    public Ativo findByIdPatrimonio(@Param("idPatrimonio") final String idPatrimonio);


    /**
     * Retorna uma lista de objetos {@link Ativo} cujo objeto {@link Categoria}
     * associado tem um campo "nomeCategoria" igual ao valor fornecido.
     *
     * @param nomeCategoria o valor a ser comparado com o campo "nomeCategoria"
     * @return uma lista de objetos {@link Ativo} correspondentes
     */
    @Query("SELECT a FROM Ativo a WHERE a.categoria.nomeCategoria like :nomeCategoria")
    public List<Ativo> findByNomeCategoria(@Param("nomeCategoria") final String nomeCategoria);


}