//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

//------------------Imports----------------------
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDateTime;

//------------------------------------------------
@Entity
@Table(name = "ativos", schema = "public")
public class Ativo extends AbstractClass {

    @Getter
    @Setter
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    @Getter
    @Setter
    @Column(name = "id_patrimonio", nullable = false, unique = true)
    private String idPatrimonio;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_patrimonio", nullable = false)
    private Condicao condicao;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status_disponibilidade", nullable = false)
    private Status status;

    @Getter
    @Setter
    @Column(name = "descricao", length = 150)
    private String descricao;

    @Getter
    @Setter
    @Column(name = "dt_entrada", nullable = false, unique = true)
    private LocalDateTime dataEntrada;

    @Getter
    @Setter
    @Column(name = "arquivo")
    private File arquivo;

    @PrePersist
    private void prePersist() {
        this.dataEntrada = LocalDateTime.now(); //perguntar pra eles se vai precisar pegar o status do patrimonio.
    }

}
