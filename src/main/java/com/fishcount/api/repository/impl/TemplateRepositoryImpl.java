package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomTemplateRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.repository.infrastructure.spec.TemplateSpec;
import com.fishcount.common.model.entity.Template;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateRepositoryImpl extends GenericImpl<Template, Integer> implements CustomTemplateRepository {

    @Override
    public Template findByTipoEnvioEmail(EnumTipoEnvioEmail tipoEnvioEmail) {
        return getSpecRepository()
                .findOne(TemplateSpec.byTipoEnvioEmail(tipoEnvioEmail))
                .orElse(null);
    }
}
