package com.fishcount.api.service.log.erros;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.classes.gerencianet.request.PayloadCobranca;
import com.fishcount.common.model.dto.PixHistoricoDTO;
import com.fishcount.common.model.entity.log.erros.PixHistorico;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public interface PixHistoricoService
        extends IAbstractService<PixHistorico, Integer, PixHistoricoDTO> {

    void incluirCobrancaException(RestClientException exception, PayloadCobranca payloadCobranca, String metodo);
}
