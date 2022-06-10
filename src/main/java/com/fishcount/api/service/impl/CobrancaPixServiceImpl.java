package com.fishcount.api.service.impl;

import com.fishcount.api.repository.TituloParcelaRepository;
import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.api.service.gerencianet.pix.cobranca.ClientCobrancaPix;
import com.fishcount.common.model.classes.gerencianet.PayloadCalendario;
import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;
import com.fishcount.common.model.classes.gerencianet.PayloadDevedor;
import com.fishcount.common.model.classes.gerencianet.PayloadValor;
import com.fishcount.common.model.classes.gerencianet.response.PayloadCobrancaResponse;
import com.fishcount.common.model.dto.CobrancaPixDTO;
import com.fishcount.common.model.entity.CobrancaPix;
import com.fishcount.common.model.entity.LocationPix;
import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.model.entity.TituloParcela;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.optional.OptionalUtil;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CobrancaPixServiceImpl extends AbstractServiceImpl<CobrancaPix, Integer, CobrancaPixDTO> implements CobrancaPixService {

    private final ClientCobrancaPix clientCobrancaPix;
    
    @Value("${pix-config.chave-pix}")
    private String chavePix;
    
    @Value("${pix-config.msg-solicitacao-padrao}")
    private String msgPadrao;

    @Override
    @Async
    public CobrancaPix gerarRegistoCobrancaPix(PagamentoParcela parcela) {
        final PayloadCobrancaResponse payload = gerarPayloadCobranca(parcela);

        final CobrancaPix cobrancaPix = gerarCobrancaPix(payload, parcela);

        return getRepository().save(cobrancaPix);

    }

    private CobrancaPix gerarCobrancaPix(final PayloadCobrancaResponse payload, final PagamentoParcela parcela) {
        final CobrancaPix cobrancaPix = new CobrancaPix();
        final TituloParcela tituloParcela = getRepository(TituloParcelaRepository.class).findByPagamentoParcela(parcela);
        final BigDecimal valorCobranca = BigDecimal.valueOf(Double.parseDouble(payload.getValor().getOriginal()));

        cobrancaPix.setChave(payload.getChave());
        cobrancaPix.setDataCriacao(DateUtil.getDate());
        cobrancaPix.setDataAlteracao(DateUtil.getDate());
        cobrancaPix.setDataExpiracao(DateUtil.add(Calendar.SECOND, payload.getCalendario().getExpiracao()));
        cobrancaPix.setObservacaoPagador(payload.getSolicitacaoPagador());
        cobrancaPix.setSaldo(valorCobranca);
        cobrancaPix.setValor(valorCobranca);
        cobrancaPix.setTxId(payload.getTxid());
        cobrancaPix.setStatusCobranca(payload.getStatus());
        cobrancaPix.setTituloParcela(tituloParcela);

        final LocationPix locationPix = getService(LocationPixService.class).incluir(payload.getLoc());
        cobrancaPix.setLocation(locationPix);

        return cobrancaPix;
    }

    private PayloadCobrancaResponse gerarPayloadCobranca(PagamentoParcela parcela) {
        PayloadCobranca payload = new PayloadCobranca();
        Usuario usuario = OptionalUtil
                .ofFallibleNullable(() -> parcela.getPagamento().getUsuario())
                .orElse(null);

        PayloadDevedor devedor = new PayloadDevedor(usuario.getNome(), usuario.getCpf());
        payload.setDevedor(devedor);

        PayloadCalendario calendario = new PayloadCalendario(calcularSegundosNoMes(parcela.getDataVencimento()));
        payload.setCalendario(calendario);

        PayloadValor valor = new PayloadValor(parcela.getValor().toString());
        payload.setValor(valor);

        payload.setSolicitacaoPagador(msgPadrao);
        payload.setChave(chavePix);

        return clientCobrancaPix.criarCobrancaImediata(payload);
    }

    private Integer calcularSegundosNoMes(Date dataVencimento) {
        final Long segundosMes = Duration
                .between(DateUtil.getDate().toInstant(), DateUtil.add(dataVencimento, Calendar.MONTH, 1)
                        .toInstant())
                .toSeconds();

        return Integer.parseInt(Long.toString(segundosMes));
    }
}
