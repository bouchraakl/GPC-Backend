//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//------------------------------------------------
@Entity
@Table(name = "beneficiarios", schema = "public")
public class Beneficiario extends AbstractEntity{
    @Getter @Setter
    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Pessoa perfil;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Pessoa responsavel;

    /*
    * BI-DIRECIONAMENTO
    * */
    @Getter @Setter
    @ManyToMany(mappedBy = "listaEspera")
    private List<Categoria> esperandoLista;
}
