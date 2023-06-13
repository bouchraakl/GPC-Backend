//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//------------------------------------------------
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "categorias", schema = "public")
@Audited
@AuditTable(value = "categorias_audit",schema = "audit")
public class Categoria extends AbstractEntity {

    @Getter
    @Setter
    @NotNull
    @Size(min = 3, max = 20, message = "Tamanho invalido!! MIN(3) | MAX(20)")
    @Column(name = "nome_categoria", nullable = false, length = 20)
    private String nomeCategoria;

    //lista de Beneficiarios que estão na lista de espera de uma Categoria específica
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "categoria_beneficiario",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "beneficiario_id"))
    private List<Beneficiario> listaEspera;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ativo> ativos;

    @Getter
    @Setter
    @NotNull(message = "Não informado a quantidade media para aviso do estoque")
    @Column(name = "max_amarelo", nullable = false)
    private int maximoAmarelo;

    @Getter
    @Setter
    @NotNull(message = "Não informado a quantidade minima para aviso do estoque")
    @Column(name = "min_amarelo", nullable = false)
    private int minimoAmarelo;
}
