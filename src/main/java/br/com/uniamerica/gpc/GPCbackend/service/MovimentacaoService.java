//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.entity.Ativo;
import br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario;
import br.com.uniamerica.gpc.GPCbackend.entity.Movimentacao;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.BeneficiarioRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

//------------------------------------------------
@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private AtivoRepository ativoRepository;

    @Transactional
    public Movimentacao novaMovimentacao(Movimentacao movimentacao){
        Assert.notNull(movimentacao.getDataCriacao(), "Data de criação não informada!");
        Assert.notNull(movimentacao.getDataEmprestimo(), "Data de empréstimo não informada!");
        Assert.notNull(movimentacao.getAtivo(), "Ativo não informado!");
        Assert.notNull(movimentacao.getAtivo().getId(), "ID do Ativo não informado!");
        Assert.notNull(movimentacao.getBeneficiario(), "Beneficiário não informado!");
        Assert.notNull(movimentacao.getBeneficiario().getId(), "ID do Beneficiário não informado!");

        final Ativo ativoById = this.ativoRepository.findById(movimentacao.getAtivo().getId()).orElse(null);
        Assert.notNull(ativoById,"Ativo não existe");

        final Beneficiario beneficiarioById = this.beneficiarioRepository.findById(movimentacao.getBeneficiario().getId()).orElse(null);
        Assert.notNull(beneficiarioById,"Beneficiario não existe");

        return this.movimentacaoRepository.save(movimentacao);
    }
}
