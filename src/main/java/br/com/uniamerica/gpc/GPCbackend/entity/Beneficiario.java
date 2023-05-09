//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true)
    private Pessoa perfil;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Pessoa responsavel;

    //categorias das quais o beneficiário está esperando em uma lista de espera
    @Getter
    @Setter
    @ManyToMany(mappedBy = "listaEspera")
    private List<Categoria> esperandoLista;
}
