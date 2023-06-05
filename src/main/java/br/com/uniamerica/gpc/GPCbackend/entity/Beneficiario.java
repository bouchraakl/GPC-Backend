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

import java.util.List;

//------------------------------------------------
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "beneficiarios", schema = "public")
@Audited
@AuditTable(value = "beneficiarios_audit",schema = "audit")
public class Beneficiario extends AbstractEntity {

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    @NotNull(message = "O campo 'perfil' não pode ser nulo.")
    private Pessoa perfil;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn
    @NotNull(message = "O campo 'responsável' não pode ser nulo.")
    private Pessoa responsavel;

    //categorias das quais o beneficiário está esperando em uma lista de espera
    @Getter
    @Setter
    @ManyToMany(mappedBy = "listaEspera")

    private List<Categoria> esperandoLista;
}
