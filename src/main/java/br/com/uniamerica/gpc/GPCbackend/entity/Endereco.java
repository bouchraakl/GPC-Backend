package br.com.uniamerica.gpc.GPCbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enderecos", schema = "public")
public class Endereco extends AbstractClass {

    @Getter@Setter
    @Column(name = "logradouros", nullable = false, length = 50)
    private String logradouro;
    @Getter@Setter
    @Column(name = "bairros", nullable = false, length = 20)
    private String bairro;
    @Getter@Setter
    @Column(name = "cidades", nullable = false, length = 20)
    private String cidade;
    @Getter@Setter
    @Column(name = "estados", nullable = false, length = 3)
    private String uf;
    @Getter@Setter
    @Column(name = "paises", nullable = false, length = 15)
    private String pais;
    @Getter@Setter
    @Column(name = "cep", nullable = false,length = 15)
    private String cep;


}
