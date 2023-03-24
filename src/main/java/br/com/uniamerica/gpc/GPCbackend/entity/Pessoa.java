package br.com.uniamerica.gpc.GPCbackend.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Pessoa extends AbstractClass {

    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String RG;
    private String CPF;

    private Endereco endereco;

}
