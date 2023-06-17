//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 50, message = "Tamanho invalido! MIN(3) MAX(50)")
    @Column( nullable = false, length = 50)
    private String logradouro;

    @Getter
    @Setter
    @Size(min = 3, max = 20, message = "Tamanho invalido! MIN(3) MAX(20)")
    @Column(nullable = false, length = 20)
    private String bairro;

    @Getter
    @Setter
    @Size(min = 3, max = 20, message = "Tamanho invalido! MIN(3) MAX(20)")
    @Column(nullable = false, length = 20)
    private String cidade;

    @Getter
    @Setter
    @Size(min = 2, max = 3, message = "Tamanho invalido! MIN(1) MAX(3)")
    @Column(nullable = false, length = 3)
    private String uf;

    @Getter
    @Setter
    @Size(min = 3, max = 15, message = "Tamanho invalido! MIN(3) MAX(15)")
    @Column(nullable = false, length = 15)
    private String pais;

    @Getter
    @Setter
    @Size(min = 3, max = 15, message = "Tamanho invalido! MIN(3) MAX(15)")
    @Column(nullable = false, length = 15)
    private String cep;



}
