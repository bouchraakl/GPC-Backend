//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------

import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

//------------------------------------------------

/**
 * @author Bouchra Akl
 */

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Valida o objeto {@link Ativo} fornecido antes de salvar.
     *
     * @param ativo o objeto {@link Ativo} a ser validado
     * @throws IllegalArgumentException se ocorrer algum erro ao realizar a validação
     */
    @Transactional
    public void validarCadastroAtivo(final Ativo ativo) {

        Ativo existingAtivo = ativoRepository.findByIdPatrimonio(ativo.getIdPatrimonio());
        Assert.isTrue(existingAtivo == null
                        || Objects.equals(existingAtivo.getId(), ativo.getId()),
                "Um ativo já está registrado com esse ID patrimônio. " +
                        "Por favor, verifique os dados informados e tente novamente.");


        ativoRepository.save(ativo);

    }

    /**
     * Valida o objeto {@link Ativo} fornecido antes de atualizá-lo.
     *
     * @param ativo o objeto {@link Ativo} a ser validado
     * @throws IllegalArgumentException se ocorrer algum erro ao realizar a validação
     */
    @Transactional
    public void validarUpdateAtivo(Ativo ativo) {

        ativo.setDataEdicao(LocalDateTime.now());

        Assert.isTrue(ativoRepository.existsById(ativo.getId()),
                "O ID do ativo especificado não foi encontrado na base de dados. " +
                        "Por favor, verifique se o ID está correto e tente novamente.");

        Assert.notNull(ativo.getCategoria().getId(), "O ID da categoria do ativo não pode ser nulo");

        Ativo existingAtivo = ativoRepository.findByIdPatrimonio(ativo.getIdPatrimonio());
        Assert.isTrue(existingAtivo == null
                        || Objects.equals(existingAtivo.getId(), ativo.getId()),
                "Um ativo já está registrado com esse ID patrimônio. " +
                        "Por favor, verifique os dados informados e tente novamente.");

        Assert.notNull(ativo.getCondicao(), "A condição do ativo não pode ser nulo");

        Assert.notNull(ativo.getStatus(), "O status de disponibilidade do ativo não pode ser nulo");

        ativoRepository.save(ativo);

    }

    /**
     * Valida se um objeto do tipo {@link Ativo} pode ser excluído do repositório.
     *
     * @param id o ID do ativo a ser excluído
     * @throws IllegalArgumentException se o ID do ativo não existir no repositório
     */

    @Transactional
    public void validarDeleteAtivo(Long id){
        final Ativo ativo = this.ativoRepository.findById(id).orElse(null);
        Assert.notNull(ativo, "Ativo informado não existe!");
        ativo.setSuspenso(true);
        this.ativoRepository.deleteById(id);
    }

    public Page<Ativo> listAll(Pageable pageable) {
        return this.ativoRepository.findAll(pageable);
    }
}
