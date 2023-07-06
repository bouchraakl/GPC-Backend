package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa, Long> {

    @Query("from Pessoa where isSuspenso = false")
    public List<Pessoa> findByAtivo();

    @Query("from Pessoa where nome like :nome")
    public List<Pessoa> findByNome(@Param("nome") final String nome);

    @Query("SELECT p FROM Pessoa p WHERE p.cpf like :cpf")
    public Pessoa findByCpf(@Param("cpf") final String cpf);


    @Query("from Pessoa where rg like :rg")
    public List<Pessoa> findByRg(@Param("rg") final String rg);

    @Query("from Pessoa where endereco.id = :id")
    public List<Pessoa> findByEnderecoId(@Param("id") final Long id);

    @Query("from Pessoa pessoa join pessoa.endereco endereco where endereco.cep like :cep")
    public List<Pessoa> findByCep(@Param("cep") final String cep);
}
