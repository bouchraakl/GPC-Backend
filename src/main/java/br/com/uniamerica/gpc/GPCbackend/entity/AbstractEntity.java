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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;

    @Getter
    @Setter
    @Column(name = "is_suspenso", nullable = false)
    private boolean isSuspenso;

    @Getter
    @Setter
    @Column(name = "dt_edicao")
    private LocalDateTime dataEdicao;

    @Getter
    @Setter
    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    private void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.isSuspenso = true;

    }

    @PreUpdate
    private void preUpdate() {
        this.dataEdicao = LocalDateTime.now();
    }

}
