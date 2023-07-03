//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend.service;

//------------------Imports----------------------
import br.com.uniamerica.gpc.GPCbackend.entity.Beneficiario;
import br.com.uniamerica.gpc.GPCbackend.repository.BeneficiarioRepository;
import br.com.uniamerica.gpc.GPCbackend.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

//------------------------------------------------
@Service
public class BeneficiarioService {
    @Autowired
    BeneficiarioRepository beneficiarioRepository;
    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Beneficiario cadastrar(Beneficiario beneficiario) {
        beneficiario.setDataCriacao(LocalDateTime.now());
        Assert.notNull(beneficiario.getResponsavel(), "Responsável não informado!");
        Assert.notNull(beneficiario.getPerfil(), "Perfil deve ser informado.");

        return this.beneficiarioRepository.save(beneficiario);
    }

    @Transactional
    public Beneficiario editar(Beneficiario beneficiario) {
        final Beneficiario beneficiarioBanco = this.beneficiarioRepository.findById(beneficiario.getId()).orElse(null);
        Assert.notNull(beneficiarioBanco, "Beneficiário não localizado!");
        Assert.isTrue(!beneficiarioBanco.getId().equals(beneficiario.getId()), "Id na URL diverge com o corpo da requisição!.");
        Assert.notNull(beneficiario.getResponsavel(), "Responsável não informado!");
        Assert.notNull(beneficiario.getPerfil(), "Perfil não informado!");
        return this.beneficiarioRepository.save(beneficiario);
    }

    @Transactional
    public ResponseEntity<?> deletar(Long id) {
        final Beneficiario beneficiario = this.beneficiarioRepository.findById(id).orElse(null);
        Assert.notNull(beneficiario, "Beneficiário informado não existe!");
        if (!this.movimentacaoRepository.findByBeneficiarioId(id).isEmpty()) {
            beneficiario.setSuspenso(true); //Existe histório de movimentação, então suspendemos.
            this.beneficiarioRepository.save(beneficiario);
            return ResponseEntity.ok("Beneficiário suspenso, pois existe histórico de empréstimo!");
        } else {
            this.beneficiarioRepository.delete(beneficiario);
            return ResponseEntity.ok("Beneficiário deletado.");
        }
    }
}