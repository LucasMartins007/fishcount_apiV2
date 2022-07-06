package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Template;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;

public interface CustomTemplateRepository {

    Template findByTipoEnvioEmail(EnumTipoEnvioEmail tipoEnvioEmail);
}
