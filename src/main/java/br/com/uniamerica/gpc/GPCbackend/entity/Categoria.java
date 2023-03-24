package br.com.uniamerica.gpc.GPCbackend.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categoria_do_patrimonio", schema = "public")
public class Categoria extends AbstractClass {

    @Getter@Setter
    @Column(name = "categorias", nullable = false)
    private String nomeCategoria;
    @Getter@Setter
    @Column(name = "lista_de_espera")
    private List<Beneficiario> listaEspera;
    @Getter@Setter
    @Column(name = "max_amarelo", nullable = false)
    private Integer maximoAmarelo;
    @Getter@Setter
    @Column(name = "min_amarelo", nullable = false) //eu acho que deveria poder ser null
    private Integer minimoAmarelo;


}
