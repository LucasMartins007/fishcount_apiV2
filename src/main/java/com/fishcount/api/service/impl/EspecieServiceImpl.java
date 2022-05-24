package com.fishcount.api.service.impl;

import com.fishcount.api.repository.EspecieRepository;
import com.fishcount.api.service.EspecieService;
import com.fishcount.api.service.TaxaCrescimentoService;
import com.fishcount.api.validators.EspecieValidator;
import com.fishcount.api.validators.TaxaCrescimentoValidator;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import com.fishcount.common.utils.DateUtil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EspecieServiceImpl extends AbstractServiceImpl<Especie, Integer, EspecieDTO> implements EspecieService {

    private final EspecieValidator especieValidator = new EspecieValidator();

    private final TaxaCrescimentoValidator validadorTaxaCrescimento = new TaxaCrescimentoValidator();

    @Override
    public Especie incluir(Especie especie) {
        especieValidator.validateInsert(especie);

        onPrepareInsert(especie);

        return getRepository().save(especie);
    }

    @Override
    public void onPrepareInsert(Especie especie) {
        especie.setDataInclusao(DateUtil.getDate());
        especie.setDataAtualizacao(DateUtil.getDate());

        TaxaCrescimento taxaCrescimento = especie.getTaxaCrescimento();
        taxaCrescimento = getService(TaxaCrescimentoService.class).findAndValidate(taxaCrescimento.getId());

        validadorTaxaCrescimento.validateInsert(taxaCrescimento);

        especie.setTaxaCrescimento(taxaCrescimento);
    }

    @Override
    public List<Especie> listarTodos() {
        return getRepository(EspecieRepository.class).findAll();
    }

    @Override
    public Especie findByDescricao(String descricao) {
        return getRepository(EspecieRepository.class).findByDescricao(descricao);
    }

}
