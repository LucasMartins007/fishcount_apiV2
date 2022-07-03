package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.ControleEmailDTO;
import com.fishcount.common.model.entity.ControleEmail;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;

public interface ControleEmailService extends IAbstractService<ControleEmail, Integer, ControleEmailDTO> {

    void enviarEmail(Pessoa pessoa, EnumTipoEnvioEmail tipoEnvioEmail);
}
