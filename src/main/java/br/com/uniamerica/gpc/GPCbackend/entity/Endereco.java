//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Logradouro precisa ser informado!")
    @NotBlank(message = "Logradouro precisa ser informado!")
    @Size(min = 3, max = 50, message = "Tamanho invalido! MIN(3) MAX(50)")
    @Column( nullable = false, length = 50)
    private String logradouro;

    @Getter
    @Setter
    @NotNull(message = "Bairro precisa ser informado!")
    @NotBlank(message = "Bairro precisa ser informado!")
    @Size(min = 3, max = 20, message = "Tamanho invalido! MIN(3) MAX(20)")
    @Column(nullable = false, length = 20)
    private String bairro;

    @Getter
    @Setter
    @NotNull(message = "Cidade precisa ser informado!")
    @NotBlank(message = "Cidade precisa ser informado!")
    @Size(min = 3, max = 20, message = "Tamanho invalido! MIN(3) MAX(20)")
    @Column(nullable = false, length = 20)
    private String cidade;

    @Getter
    @Setter
    @NotNull(message = "Estado precisa ser informado!")
    @NotBlank(message = "Estado precisa ser informado!")
    @Size(min = 2, max = 3, message = "Tamanho invalido! MIN(1) MAX(3)")
    @Column(nullable = false, length = 3)
    private String uf;

    @Getter
    @Setter
    @NotNull(message = "Numero precisa ser informado!")
    @Column(nullable = false, length = 7)
    private Integer numero;

    @Getter
    @Setter
    @NotNull(message = "País precisa ser informado!")
    @NotBlank(message = "País precisa ser informado!")
    @Size(min = 3, max = 15, message = "Tamanho invalido! MIN(3) MAX(15)")
    @Column(nullable = false, length = 15)
    private String pais;

    @Getter
    @Setter
    @NotNull(message = "CEP precisa ser informado!")
    @NotBlank(message = "CEP precisa ser informado!")
    @Size(min = 3, max = 15, message = "Tamanho invalido! MIN(3) MAX(15)")
    @Column(nullable = false, length = 15)
    private String cep;


    @Getter
    @Setter
    @Size(min = 3, max = 15, message = "Tamanho invalido! MIN(3) MAX(15)")
    @Column(length = 15)
    private String complemento;



}
