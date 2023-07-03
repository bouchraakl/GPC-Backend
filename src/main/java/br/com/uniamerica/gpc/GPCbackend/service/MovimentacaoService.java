//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.entity.*;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.BeneficiarioRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author Jean Moschen
 * */
//------------------------------------------------
@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private AtivoRepository ativoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Movimentacao novaMovimentacao(Movimentacao movimentacao){
        movimentacao.setDataCriacao(LocalDateTime.now());
        Assert.notNull(movimentacao.getDataEmprestimo(), "Data de empréstimo não informada!");
        Assert.notNull(movimentacao.getDataDevolucao(), "Data de devolução não informada!");

        Assert.notNull(movimentacao.getAtivo(), "Ativo não informado!");
        Assert.notNull(movimentacao.getAtivo().getId(), "ID do Ativo não informado!");

        Assert.notNull(movimentacao.getBeneficiario(), "Beneficiário não informado!");
        Assert.notNull(movimentacao.getBeneficiario().getId(), "ID do Beneficiário não informado!");

        Assert.isTrue(movimentacao.getDataDevolucao().isAfter(movimentacao.getDataEmprestimo()), "Data de devolução não pode ser maior que data de empréstimo!");

        final Pessoa beneficiarioById = this.pessoaRepository.findById(movimentacao.getBeneficiario().getId()).orElse(null);
        Assert.notNull(beneficiarioById,"Beneficiario não existe");
        Assert.isTrue(!beneficiarioById.isSuspenso(), "Beneficiário está desativado!");
//        Assert.notNull(beneficiarioById.getResponsavel(), "Responsável não informado!");
//        Assert.isTrue(!beneficiarioById.getResponsavel().isSuspenso(), "Responsável desativado!");


        final Ativo ativoById = this.ativoRepository.findById(movimentacao.getAtivo().getId()).orElse(null);
        Assert.notNull(ativoById,"Ativo não existe");
        Assert.isTrue(!ativoById.isSuspenso(),"Ativo desativado!");
        ativoById.setStatus(Status.USANDO);
        this.ativoRepository.save(ativoById);

        return this.movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public Movimentacao editar(Long id, Movimentacao movimentacao){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);
        Assert.notNull(movimentacaoBanco, "Movimentação informada não existe!");

        Assert.isTrue(movimentacao.getId().equals(movimentacaoBanco.getId()), "ID informado na URL da requisição não condiz com ID informado no corpo da requisição");

        Assert.notNull(movimentacao.getDataEmprestimo(), "Data de empréstimo não informada!");
        Assert.notNull(movimentacao.getDataDevolucao(), "Data de devolução não informada!");
        Assert.notNull(movimentacao.getAtivo(), "Ativo não informado!");
        Assert.notNull(movimentacao.getAtivo().getId(), "ID do Ativo não informado!");
        Assert.notNull(movimentacao.getBeneficiario(), "Beneficiário não informado!");
        Assert.notNull(movimentacao.getBeneficiario().getId(), "ID do Beneficiário não informado!");

        return this.movimentacaoRepository.save(movimentacao);
    }
    @Transactional
    public Movimentacao desativar(Long id){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);
        Assert.notNull(movimentacaoBanco, "Movimentação informada não existe!");

        movimentacaoBanco.setSuspenso(true);
        return this.movimentacaoRepository.save(movimentacaoBanco);
    }
}
