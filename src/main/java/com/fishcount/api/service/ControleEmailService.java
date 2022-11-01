package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.ControleEmailDTO;
import com.fishcount.common.model.entity.ControleEmail;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import org.springframework.scheduling.annotation.Async;

public interface ControleEmailService extends IAbstractService<ControleEmail, Integer, ControleEmailDTO> {

    @Async
    void enviarEmail(Pessoa pessoa, EnumTipoEnvioEmail tipoEnvioEmail, boolean isSuporte);
}
