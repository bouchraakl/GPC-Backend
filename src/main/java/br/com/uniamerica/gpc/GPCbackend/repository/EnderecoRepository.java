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

    @Query("from Endereco where cep = :cep")
    public List<Endereco> findByCep(@Param("cep") String cep);

    @Query("from Endereco where Endereco = :Endereco")
    public List<Endereco> findByAll(@Param("lista") String Endereco);

    @Query("from Endereco where logradouro = :logradouro")
    public List<Endereco> findByLogradouro(@Param("logradouro") String logradouro);

    @Query("from Endereco where bairro = :bairro")
    public List<Endereco> findByBairro(@Param("bairro") String Bairro);

    @Query("from Endereco where cidade = :cidade")
    public List<Endereco> findByCidade(@Param("cidade") String Cidade);

    @Query("from Endereco where uf = :uf")
    public List<Endereco> findByUf(@Param("uf") String uf);

    @Query("from Endereco where pais = :pais")
    public List<Endereco> findByPais(@Param("pais") String pais);


}
