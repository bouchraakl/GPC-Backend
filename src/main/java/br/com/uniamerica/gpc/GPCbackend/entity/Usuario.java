package br.com.uniamerica.gpc.GPCbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario extends Pessoa {

    @Getter@Setter
    @Column(name = "logins", nullable = false, unique = true)
    private String login;
    @Getter@Setter
    @Column(name = "senhas", nullable = false, unique = true)
    private String password;

}
