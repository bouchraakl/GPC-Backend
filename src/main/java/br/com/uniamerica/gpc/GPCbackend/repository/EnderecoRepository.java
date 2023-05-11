package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Endereco;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {

    @Query("from Endereco where cep like :cep")
    public Endereco findByCep(@Param("cep") String cep);

    @Query("from Endereco where Endereco = :Endereco")
    public Endereco findByAll(@Param("lista") String Endereco);

    @Query("from Endereco where logradouro = :logradouro")
    public Endereco findByLogradouro(@Param("logradouro") String logradouro);

    @Query("from Endereco where bairro = :bairro")
    public Endereco findByBairro(@Param("bairro") String Bairro);

    @Query("from Endereco where cidade = :cidade")
    public Endereco findByCidade(@Param("cidade") String Cidade);

    @Query("from Endereco where uf = :uf")
    public Endereco findByUf(@Param("uf") String uf);

    @Query("from Endereco where pais = :pais")
    public Endereco findByPais(@Param("pais") String pais);


}
