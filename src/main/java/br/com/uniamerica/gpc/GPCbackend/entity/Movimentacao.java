//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;

//------------------------------------------------
@Entity
@Table(name = "movimentacoes", schema = "public")
public class    Movimentacao extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "dt_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Getter
    @Setter
    @Column(name = "dt_devolucao", nullable = false)
    private LocalDate dataDevolucao;

    @Getter
    @Setter
    @Column(name = "devolvido", nullable = false)
    private boolean isDevolvido;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;

    @Getter
    @Setter
    @Column(name = "descricao", length = 150)
    private String descricao;

    @Getter
    @Setter
    @Column(name = "arquivos")
    private File arquivo;


}
