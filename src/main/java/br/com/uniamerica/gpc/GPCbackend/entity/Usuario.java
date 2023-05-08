//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

//------------------------------------------------
@Entity
@Table(name = "usuarios", schema = "public")
@Audited
@AuditTable(value = "usuarios_audit",schema = "audit")
public class Usuario extends AbstractEntity {

    @Getter @Setter
    @OneToOne
    @JoinColumn(nullable = false,unique = true)
    private Pessoa perfil;

    @Getter
    @Setter
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Getter
    @Setter
    @Column(name = "password", nullable = false, unique = true)
    private String password;

}
