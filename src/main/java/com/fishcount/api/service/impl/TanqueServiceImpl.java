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
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TanqueServiceImpl extends AbstractServiceImpl<Tanque, Integer, TanqueDTO> implements TanqueService {

    private final TanqueValidator tanqueValidator;
    
    private final EspecieValidator especieValidator;

    @Override
    public Tanque incluir(Integer loteId, Tanque tanque) {
        onPrepareInsert(loteId, tanque);

        tanqueValidator.validateInsert(tanque);

        return getRepository().save(tanque);
    }

    @Override
    public void editar(Integer pessoaId, Integer loteId, Integer tanqueId, Tanque tanque) {
        onPrepareUpdate(loteId, tanqueId, tanque);

        tanqueValidator.validateInsertOrUpdate(tanque);

        getRepository().save(tanque);
    }

    @Override
    public List<Tanque> listarFromPessoaAndLote(Integer pessoaId, Integer loteId) {
        return getRepository(TanqueRepository.class).findAllByPessoaAndLote(pessoaId, loteId);
    }

    @Override
    public Tanque encontrarPorId(Integer pessoaId, Integer loteId, Integer tanqueId) {
        return getRepository(TanqueRepository.class).findByPessoaAndLoteAndId(pessoaId, loteId, tanqueId);
    }

    @Override
    public void inativarTanque(Integer pessoaId, Integer loteId, Integer tanqueId) {
        final Tanque tanque = getRepository(TanqueRepository.class).findByPessoaAndLoteAndId(pessoaId, loteId, tanqueId);
        OptionalUtil.ofNullable(tanque)
                .peekIfPresent(managedTanque -> getRepository().delete(managedTanque))
                .ifAbsentThrow(() -> new FcRuntimeException(EnumFcDomainException.TANQUE_NAO_ENCONTRADO, tanqueId));
    }

    private void onPrepareInsert(Integer loteId, Tanque tanque) {
        final Lote lote = getService(LoteService.class).findAndValidate(loteId);
        tanque.setLote(lote);

        if (Utils.isNotEmpty(tanque.getEspecie())) {
            final Especie especie = getService(EspecieService.class).findAndValidate(tanque.getEspecie().getId());
            tanque.setEspecie(especie);
        }
        tanque.setQtdUltimaAnalise(0);
        tanque.setDataProximaAnalise(DateUtil.getDate());
        tanque.setDataUltimaAnalise(DateUtil.getDate());
        tanque.setDataUltimoTratamento(DateUtil.getDate());
    }

    private void onPrepareUpdate(Integer loteId, Integer tanqueId, Tanque tanque){
        final Lote lote = getService(LoteService.class).findAndValidate(loteId);
        tanque.setLote(lote);

        final Tanque managedTanque = findAndValidate(tanqueId);
        tanque.setDataInclusao(managedTanque.getDataInclusao());
        tanque.setAnalise(managedTanque.getAnalise());
        tanque.setDataAtualizacao(managedTanque.getDataAtualizacao());
        tanque.setDataProximaAnalise(managedTanque.getDataProximaAnalise());
        tanque.setDataAtualizacao(managedTanque.getDataAtualizacao());
        tanque.setDataUltimaAnalise(managedTanque.getDataUltimaAnalise());
        tanque.setDataUltimoTratamento(managedTanque.getDataUltimoTratamento());
        tanque.setId(managedTanque.getId());
    }

}
