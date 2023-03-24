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
    @Column(name = "logradouro", nullable = false, length = 50)
    private String logradouro;

    @Getter
    @Setter
    @Column(name = "bairro", nullable = false, length = 20)
    private String bairro;

    @Getter
    @Setter
    @Column(name = "cidade", nullable = false, length = 20)
    private String cidade;

    @Getter
    @Setter
    @Column(name = "estado", nullable = false, length = 3)
    private String uf;

    @Getter
    @Setter
    @Column(name = "paise", nullable = false, length = 15)
    private String pais;

    @Getter
    @Setter
    @Column(name = "cep", nullable = false, length = 15)
    private String cep;


}
