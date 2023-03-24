//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//------------------------------------------------
@Entity
@Table(name = "beneficiarios", schema = "public")
public class Beneficiario extends Pessoa {

    @Getter
    @Setter
    @Column(name = "responsavel", nullable = false)
    private Responsavel responsavel;


}
