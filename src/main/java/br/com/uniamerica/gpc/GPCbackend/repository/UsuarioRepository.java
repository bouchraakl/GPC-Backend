package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario;
import br.com.uniamerica.gpc.GPCbackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    @Query("from Usuario usuario join usuario.perfil perfil where perfil.nome like :nome")
    public List<Usuario> findByNome(@Param("nome") String nome);

    @Query("from Usuario usuario join usuario.perfil perfil where perfil.cpf like :cpf")
    public List<Usuario> findByCpf(@Param("cpf") String cpf);


}
