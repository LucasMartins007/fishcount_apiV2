package com.fishcount.api.service.impl.log.erros;

import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.service.log.erros.PixHistoricoService;
import com.fishcount.common.model.classes.gerencianet.request.PayloadCobranca;
import com.fishcount.common.model.dto.PixHistoricoDTO;
import com.fishcount.common.model.entity.Pessoa;
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
        pixHistorico.setValor(BigDecimal.valueOf(Double.parseDouble(payloadCobranca.getValor().getOriginal())));

        final String cpf = payloadCobranca.getDevedor().getCpf();
        final Pessoa pessoa = getRepository(PessoaRepository.class).findByCpf(cpf);
        pixHistorico.setPessoa(pessoa);
        pixHistorico.setAssinaturaMetodo(assinaturaMetodo);

        getRepository().save(pixHistorico);
    }
}
