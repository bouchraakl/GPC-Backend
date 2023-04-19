package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {
}
