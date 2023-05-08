//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//------------------------------------------------
@Entity
@Table(name = "categorias", schema = "public")
public class Categoria extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "nome_categoria", nullable = false, length = 20)
    private String nomeCategoria;

    //lista de Beneficiarios que estão na lista de espera de uma Categoria específica
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "categoria_beneficiario",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "beneficiario_id"))
    private List<Beneficiario> listaEspera;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ativo> ativos;

    @Getter
    @Setter
    @Column(name = "max_amarelo", nullable = false)
    private int maximoAmarelo;

    @Getter
    @Setter
    @Column(name = "min_amarelo", nullable = false)
    private int minimoAmarelo;
}
