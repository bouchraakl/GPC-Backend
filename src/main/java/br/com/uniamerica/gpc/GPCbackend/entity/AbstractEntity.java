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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Getter
    @Setter
    @Column(name = "suspenso", nullable = false)
    private boolean isSuspenso;

    @Getter
    @Setter
    @Column(name = "editor", nullable = false)
    private Usuario editor;

    @Getter
    @Setter
    @Column(name = "dt_edicao", nullable = false)
    private LocalDateTime dataEdicao;

    @Getter
    @Setter
    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    /*
     * Ver com o pessoal se é pra salvar quem editou
     * e ver com o professor como que faria pra salvar
     */
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
