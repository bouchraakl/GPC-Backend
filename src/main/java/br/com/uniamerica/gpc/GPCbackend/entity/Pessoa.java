//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

//------------------------------------------------
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "pessoas",schema = "public")
@Audited
@AuditTable(value = "pessoas_audit",schema = "audit")
public class Pessoa extends AbstractEntity {

    @Getter
    @Setter
    @NotBlank(message = "Nome foi informado vazio!")
    @NotNull(message = "Informe o nome da Pessoa!")
    @Column(name = "nome",nullable = false,length = 50)
    private String nome;

    @Getter
    @Setter
    @Email(message = "Email informado não é válido!")
    @Column(name = "email" , length = 50)
    private String email;

    @Getter
    @Setter
    @NotBlank(message = "Telefone foi informado vazio!")
    @NotNull(message = "Telefone deve ser informado!")
    @Column(name = "telefone" , length = 25, nullable = false)
    private String telefone;

    @NotNull(message = "Data de Nascimento deve ser informada!")
    @NotBlank(message = "Data de nascimento foi informada vazia!")
    @Getter
    @Setter
    @Column(name = "dt_nascimento" , length = 50)
    private LocalDate dataNascimento;

    @NotNull(message = "Informe o RG!")
    @NotBlank(message = "RG informado em branco!")
    @Getter
    @Setter
    @Column(name = "rg" , length = 20,unique = true, nullable = false)
    private String rg;

    @NotNull(message = "Informe o CPF!")
    @NotBlank(message = "CPF informado em branco!")
    @Getter
    @Setter
    @Column(name = "cpf" , length = 15,nullable = false,unique = true)
    private String cpf;

    @NotNull(message = "Informe o endereço!")
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id" ,nullable = false)
    private Endereco endereco;
}
