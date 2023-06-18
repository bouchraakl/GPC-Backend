//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//------------------------------------------------
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Getter
    @SequenceGenerator(name = "gpc_sequence",sequenceName = "gpc_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gpc_sequence")
    @Column(name = "id", unique = true)
    private Long id;

    @Getter
    @Setter
    @Column(name = "is_suspenso")
    private boolean isSuspenso;

    @Getter
    @Setter
    @Column(name = "dt_edicao")
    private LocalDateTime dataEdicao;

    @Getter
    @Setter
    @Column(name = "dt_criacao")
    private LocalDateTime dataCriacao;

    @PrePersist
    private void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.isSuspenso = false;
    }

    @PreUpdate
    private void preUpdate() {
        this.dataEdicao = LocalDateTime.now();
    }

}
