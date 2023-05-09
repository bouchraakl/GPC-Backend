//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

//------------------------------------------------
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "enderecos", schema = "public")
@Audited
@AuditTable(value = "enderecos_audit",schema = "audit")
public class Endereco extends AbstractEntity {

    @Getter
    @Setter
    @Column( nullable = false, length = 50)
    private String logradouro;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String bairro;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String cidade;

    @Getter
    @Setter
    @Column(nullable = false, length = 3)
    private String uf;

    @Getter
    @Setter
    @Column(nullable = false, length = 15)
    private String pais;

    @Getter
    @Setter
    @Column(nullable = false, length = 15)
    private String cep;


}
