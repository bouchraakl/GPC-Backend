//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

//------------------------------------------------
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "movimentacoes", schema = "public")
@Audited
@AuditTable(value = "movimentacoes_audit",schema = "audit")
public class Movimentacao extends AbstractEntity {
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
    @NotNull
    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;

    @Getter
    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;

    @Getter
    @Setter
    @Length(min = 0, max = 150, message = "A descrição deve ter no máximo 150 caracteres")
    @Column(name = "descricao", length = 150)
    private String descricao;
}
