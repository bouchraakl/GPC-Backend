package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiarioRepository extends JpaRepository <Beneficiario, Long> {
    @Query("select beneficiario from Beneficiario beneficiario join beneficiario.perfil perfil where perfil.nome = :nome")
    public List<Beneficiario> findByNome(@Param("nome") String nome);
}
