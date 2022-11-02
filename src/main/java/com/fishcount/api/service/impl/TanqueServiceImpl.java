package com.fishcount.api.service.impl;

import com.fishcount.api.repository.TanqueRepository;
import com.fishcount.api.service.EspecieService;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.TanqueService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.EspecieValidator;
import com.fishcount.api.validators.TanqueValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TanqueServiceImpl
        extends AbstractServiceImpl<Tanque, Integer, TanqueDTO>
        implements TanqueService {

    private final TanqueValidator tanqueValidator;

    private final EspecieValidator especieValidator;

    private final TanqueRepository tanqueRepository;

    private final LoteService loteService;

    private final EspecieService especieService;

    @Override
    public Tanque incluir(Integer loteId, Tanque tanque) {
        onPrepareInsert(loteId, tanque);

        tanqueValidator.validateInsert(tanque);

        return tanqueRepository.save(tanque);
    }

    @Override
    public void editar(Integer pessoaId, Integer loteId, Integer tanqueId, Tanque tanque) {
        onPrepareUpdate(loteId, tanqueId, tanque);

        tanqueValidator.validateInsertOrUpdate(tanque);

        tanqueRepository.save(tanque);
    }

    @Override
    public List<Tanque> listarFromPessoaAndLote(Integer pessoaId, Integer loteId, String orderBy) {
        if (Utils.isNotEmpty(orderBy)) {
            return tanqueRepository.findAllByPessoaAndLoteOrderBy(pessoaId, loteId, orderBy);
        }
        return tanqueRepository.findAllByPessoaAndLote(pessoaId, loteId);
    }

    @Override
    public Tanque encontrarPorId(Integer pessoaId, Integer loteId, Integer tanqueId) {
        return tanqueRepository.findByPessoaAndLoteAndId(pessoaId, loteId, tanqueId);
    }

    @Override
    public void inativarTanque(Integer pessoaId, Integer loteId, Integer tanqueId) {
        final Tanque tanque = tanqueRepository.findByPessoaAndLoteAndId(pessoaId, loteId, tanqueId);
        OptionalUtil.ofNullable(tanque)
                .peekIfPresent(managedTanque -> {
                    managedTanque.setAtivo(false);
                    managedTanque.setDataAtualizacao(DateUtil.getDate());
                    tanqueRepository.save(managedTanque);
                })
                .ifAbsentThrow(() -> new FcRuntimeException(EnumFcDomainException.TANQUE_NAO_ENCONTRADO, tanqueId));
    }

    private void onPrepareInsert(Integer loteId, Tanque tanque) {
        final Lote lote = loteService.findAndValidate(loteId);
        tanque.setLote(lote);

        if (Utils.isNotEmpty(tanque.getEspecie())) {
            final Especie especie = especieService.findAndValidate(tanque.getEspecie().getId());
            tanque.setEspecie(especie);
        }
        tanque.setAtivo(true);
        tanque.setQtdUltimaAnalise(tanque.getQtdePeixe());
        tanque.setStatusAnalise(EnumStatusAnalise.ANALISE_NAO_REALIZADA);
        tanque.setDataProximaAnalise(DateUtil.getDate());
        tanque.setDataUltimaAnalise(DateUtil.getDate());
        tanque.setDataUltimoTratamento(DateUtil.getDate());
    }

    private void onPrepareUpdate(Integer loteId, Integer tanqueId, Tanque tanque) {
        final Lote lote = loteService.findAndValidate(loteId);
        tanque.setLote(lote);

        final Tanque managedTanque = findAndValidate(tanqueId);
        tanque.setAtivo(true);
        tanque.setStatusAnalise(managedTanque.getStatusAnalise());
        tanque.setQtdUltimaAnalise(managedTanque.getQtdUltimaAnalise());
        tanque.setDataInclusao(managedTanque.getDataInclusao());
        tanque.setAnalises(managedTanque.getAnalises());
        tanque.setDataAtualizacao(DateUtil.getDate());
        tanque.setDataProximaAnalise(managedTanque.getDataProximaAnalise());
        tanque.setDataUltimaAnalise(managedTanque.getDataUltimaAnalise());
        tanque.setDataUltimoTratamento(managedTanque.getDataUltimoTratamento());
        tanque.setId(managedTanque.getId());
    }

    @Override
    public Tanque findAndValidate(Integer tanqueId) {
        return tanqueRepository
                .findById(tanqueId)
                .orElseThrow(() -> new FcRuntimeException(EnumFcInfraException.ENTITY_NOT_FOUND, Tanque.class.getSimpleName(), tanqueId));
    }

}
