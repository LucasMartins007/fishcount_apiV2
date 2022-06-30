package com.fishcount.api.service.impl;

import com.fishcount.api.repository.TanqueRepository;
import com.fishcount.api.service.EspecieService;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.TanqueService;
import com.fishcount.api.validators.EspecieValidator;
import com.fishcount.api.validators.TanqueValidator;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.Utils;
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
    public List<Tanque> listarFromLote(Integer loteId) {
        Lote lote = getService(LoteService.class).findAndValidate(loteId);

        return getRepository(TanqueRepository.class).findAllByLote(lote);
    }

    private void onPrepareInsert(Integer loteId, Tanque tanque) {
        Lote lote = getService(LoteService.class).findAndValidate(loteId);
        tanque.setLote(lote);

        if (Utils.isNotEmpty(tanque.getEspecie())) {
            Especie especie = tanque.getEspecie();
            especieValidator.validateInsert(especie);
            especie = getService(EspecieService.class).findAndValidate(especie.getId());
            getService(EspecieService.class).onPrepareInsert(especie);
            
            tanque.setEspecie(especie);
        }
        tanque.setQtdUltimaAnalise(0);
        tanque.setDataProximaAnalise(DateUtil.getDate());
        tanque.setDataUltimaAnalise(DateUtil.getDate());
        tanque.setDataUltimoTratamento(DateUtil.getDate());
    }

}
