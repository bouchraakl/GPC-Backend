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
@Table(name = "enderecos", schema = "public")
public class Endereco extends AbstractEntity {

    @Getter
    @Setter
    @Column( nullable = false, length = 50)
    private String logradouro;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String bairro;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String cidade;

    @Getter
    @Setter
    @Column(nullable = false, length = 3)
    private String uf;

    @Getter
    @Setter
    @Column(nullable = false, length = 15)
    private String pais;

    @Getter
    @Setter
    @Column(nullable = false, length = 15)
    private String cep;


}
