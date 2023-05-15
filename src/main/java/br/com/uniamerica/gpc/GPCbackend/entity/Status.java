//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.entity;

import jakarta.validation.constraints.NotNull;

//------------------------------------------------
@NotNull
public enum Status {
    USANDO,
    DISPONIVEL,
    INUTILIZAVEL,
    MANUTENCAO

}
