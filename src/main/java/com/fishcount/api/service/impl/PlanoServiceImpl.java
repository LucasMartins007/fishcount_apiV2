package com.fishcount.api.service.impl;

import com.fishcount.api.repository.PlanoRepository;
import com.fishcount.api.service.PlanoService;
import com.fishcount.api.validators.PlanoValidator;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoServiceImpl extends AbstractServiceImpl<Plano, Integer, PlanoDTO> implements PlanoService {

    private final PlanoValidator planoValidator;

    @Override
    public List<Plano> listarPlanos() {
        return getRepository(PlanoRepository.class).findAllAtivos();
    }

    @Override
    public Plano incluir(Plano plano) {
        onPrepareInsert(plano);

        return getRepository().save(plano);
    }

    private void onPrepareInsert(Plano plano) {
        planoValidator.validateInsert(plano);
    }

}
