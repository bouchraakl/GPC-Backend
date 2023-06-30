//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

//------------------------------------------------
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "usuarios", schema = "public")
@Audited
@AuditTable(value = "usuarios_audit",schema = "audit")
public class Usuario extends AbstractEntity {

    @NotNull(message = "Informe o perfil!")
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "pessoa_id", nullable = false,unique = true)
    private Pessoa perfil;

    @NotNull(message = "Informe o usuário!")
    @NotBlank(message = "Usuário informado vazio!")
    @Length(min = 4, max = 15, message = "O usuário deve ter entre 4 e 15 caracteres")
    @Getter
    @Setter
    @Column(name = "login", nullable = false, unique = true, length = 15)
    private String login;

    @NotNull(message = "Informe a senha!")
    @NotBlank(message = "Senha informado vazia!")
    @Length(min = 8, max = 15, message = "A senha deve ter entre 5 e 15 caracteres")
    @Getter
    @Setter
    @Column(name = "password", nullable = false, length = 15)
    private String password;

}
