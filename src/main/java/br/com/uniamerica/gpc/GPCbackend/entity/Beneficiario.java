package br.com.uniamerica.gpc.GPCbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "beneficiarios", schema = "public")
public class Beneficiario extends Pessoa {

    @Getter@Setter
    @Column(name = "beneficiario", nullable = false) //eu acho que aqui nao precisa do nome, mas ver com o professor
    private Pessoa responsavel;


}
