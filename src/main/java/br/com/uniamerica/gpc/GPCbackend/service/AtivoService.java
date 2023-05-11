//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Condicao;
import br.com.uniamerica.gpc.GPCbackend.entity.Status;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

//------------------------------------------------

/**
 * @author Bouchra Akl
 */

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    /**
     * Valida o objeto {@link Ativo} fornecido antes de salvar.
     *
     * @param ativo o objeto {@link Ativo} a ser validado
     * @throws IllegalArgumentException se ocorrer algum erro ao realizar a validação
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void validarCadastroAtivo(final Ativo ativo) {

        Assert.notNull(ativo.getDataCriacao(),"Data de Criação não informada.");

        Assert.notNull(ativo.getCategoria(), "A categoria do ativo não pode ser nula");
        Assert.notNull(ativo.getCategoria().getId(), "O ID da categoria do ativo não pode ser nulo");

        Assert.notNull(ativo.getIdPatrimonio(), "O ID do patrimônio do ativo não pode ser nulo");
        Assert.hasText(ativo.getIdPatrimonio(),"Campo Id Patrimonio não preenchido.");


        Ativo existingAtivo = ativoRepository.findByIdPatrimonio(ativo.getIdPatrimonio());
        Assert.isTrue(existingAtivo == null || Objects.equals(existingAtivo.getId(), ativo.getId()),
                "O ID do patrimônio já está sendo usado por outro ativo");


        Assert.notNull(ativo.getCondicao(), "A condição do ativo não pode ser nula");

        Assert.notNull(ativo.getStatus(), "O status de disponibilidade do ativo não pode ser nulo");

        ativo.setDataEntrada(LocalDateTime.now());

    }

    /**
     * Valida o objeto {@link Ativo} fornecido antes de atualizá-lo.
     *
     * @param ativo o objeto {@link Ativo} a ser validado
     * @throws IllegalArgumentException se ocorrer algum erro ao realizar a validação
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void validarUpdateAtivo(Ativo ativo) {

        Assert.notNull(ativo, "O ativo não pode ser nulo");

        Assert.notNull(ativo.getId(), "O ID do ativo não pode ser nulo");

        Assert.notNull(ativo.getIdPatrimonio(), "O ID do patrimônio do ativo não pode ser nulo");
        Ativo existingAtivo = ativoRepository.findByIdPatrimonio(ativo.getIdPatrimonio());
        Assert.isTrue(existingAtivo == null || Objects.equals(existingAtivo.getId(), ativo.getId()),
                "O ID do patrimônio já está sendo usado por outro ativo");

        Assert.notNull(ativo.getCondicao(), "A condição do ativo não pode ser nulo");
        Assert.notNull(ativo.getStatus(), "O status de disponibilidade do ativo não pode ser nulo");


    }

    /**
     * Valida se um objeto do tipo {@link Ativo} pode ser excluído do repositório.
     *
     * @param id o ID do ativo a ser excluído
     * @throws IllegalArgumentException se o ID do ativo não existir no repositório
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void validarDeleteAtivo(Long id) {

        Assert.isTrue(ativoRepository.existsById(id), "O ID ativo nao existe");
    }
}
