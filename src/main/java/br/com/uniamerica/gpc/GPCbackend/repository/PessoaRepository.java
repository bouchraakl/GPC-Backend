package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
    @Query("from Pessoa where nome like :nome")
    public List<Pessoa> findByNome(@Param("nome") final String nome);

    @Query("from Pessoa where cpf like :cpf")
    public List<Pessoa> findByCpf(@Param("cpf") final String cpf);

    @Query("from Pessoa pessoa join pessoa.endereco endereco where endereco.cep like :cep")
    public List<Pessoa> findByCep(@Param("cep") final String cep);

}
