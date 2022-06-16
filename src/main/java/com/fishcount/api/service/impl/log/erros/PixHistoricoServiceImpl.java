package com.fishcount.api.service.impl.log.erros;

import com.fishcount.api.repository.UsuarioRepository;
import com.fishcount.api.service.PixService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.service.impl.AbstractServiceImpl;
import com.fishcount.api.service.log.erros.PixHistoricoService;
import com.fishcount.common.model.classes.gerencianet.request.PayloadCobranca;
import com.fishcount.common.model.dto.PixHistoricoDTO;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.entity.log.erros.PixHistorico;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;

@Service
public class PixHistoricoServiceImpl
        extends AbstractServiceImpl<PixHistorico, Integer, PixHistoricoDTO>
        implements PixHistoricoService {

    @Override
    public void incluirCobrancaException(RestClientException exception, PayloadCobranca payloadCobranca, String assinaturaMetodo) {
        final PixHistorico pixHistorico = new PixHistorico();
        pixHistorico.setMensagem(exception.getLocalizedMessage());
        pixHistorico.setValor(BigDecimal.valueOf(Long.parseLong(payloadCobranca.getValor().getOriginal())));

        final String cpf = payloadCobranca.getDevedor().getCpf();
        final Usuario usuario = getRepository(UsuarioRepository.class).findByCpf(cpf);
        pixHistorico.setUsuario(usuario);
        pixHistorico.setAssinaturaMetodo(assinaturaMetodo);

        getRepository().save(pixHistorico);
    }
}
