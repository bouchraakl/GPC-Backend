//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//------------------------------------------------
@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario extends AbstractEntity {

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
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
