package br.com.uniamerica.gpc.GPCbackend.repository;

import br.com.uniamerica.gpc.GPCbackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
}
