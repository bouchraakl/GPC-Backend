//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//------------------------------------------------
@Entity
@Table(name = "categorias", schema = "public")
public class Categoria extends AbstractClass {

    @Getter
    @Setter
    @Column(name = "nome_categoria", nullable = false)
    private String nomeCategoria;

    @Getter
    @Setter
    @Column(name = "lista_de_espera")
    private List<Beneficiario> listaEspera;

    @Getter
    @Setter
    @Column(name = "max_amarelo", nullable = false)
    private int maximoAmarelo;

    @Getter
    @Setter
    @Column(name = "min_amarelo", nullable = false)
    private int minimoAmarelo;


}
