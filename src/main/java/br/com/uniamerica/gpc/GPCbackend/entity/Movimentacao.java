package br.com.uniamerica.gpc.GPCbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "movimentacoes", schema = "public")
public class Movimentacao extends AbstractClass {
    @Getter@Setter
    @Column(name = "dt_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;
    @Getter@Setter
    @Column(name = "dt_devolucao",nullable = false)
    private LocalDate dataDevolucao;
    @Getter@Setter
    @Column(name = "devolvido",nullable = false)
    private boolean isDevolvido;
    @Getter@Setter
    @Column(name = "beneficiario",nullable = false)
    private Beneficiario beneficiario;
    @Getter@Setter
    @Column(name = "produto",nullable = false)
    private Ativo ativo;
    @Getter@Setter
    @Column(name = "descricao")
    private String descricao;
    @Getter@Setter
    @Column(name = "responsavel",nullable = false)
    private Pessoa responsavel;
    @Getter@Setter
    @Column(name = "arquivos")
    private Byte[] arquivo;



}
