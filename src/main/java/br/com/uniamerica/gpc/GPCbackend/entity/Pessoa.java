//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//------------------------------------------------
@Entity
@Table(name = "pessoas",schema = "public")
public class Pessoa extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "nome",nullable = false,length = 50)
    private String nome;
    @Getter
    @Setter
    @Column(name = "email" , length = 50)
    private String email;

    @Getter
    @Setter
    @Column(name = "telefone" , length = 20)
    private String telefone;

    @Getter
    @Setter
    @Column(name = "dt_nascimento" , length = 50)
    private LocalDate dataNascimento;

    @Getter
    @Setter
    @Column(name = "rg" , length = 15,unique = true)
    private String rg;

    @Getter
    @Setter
    @Column(name = "cpf" , length = 15,nullable = false,unique = true)
    private String cpf;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id" ,nullable = false)
    private Endereco endereco;







}
