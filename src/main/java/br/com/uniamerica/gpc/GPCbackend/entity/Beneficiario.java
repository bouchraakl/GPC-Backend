//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//------------------------------------------------
@Entity
@Table(name = "beneficiarios", schema = "public")
public class Beneficiario extends Pessoa {

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "responsavel_id",nullable = false)
    private Responsavel responsavel;


}
