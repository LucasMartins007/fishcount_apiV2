package com.fishcount.api.service.impl;

import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.api.service.gerencianet.pix.cobranca.ClientCobrancaPix;
import com.fishcount.common.model.classes.gerencianet.request.PayloadCalendario;
import com.fishcount.common.model.classes.gerencianet.request.PayloadCobranca;
import com.fishcount.common.model.classes.gerencianet.request.PayloadDevedor;
import com.fishcount.common.model.classes.gerencianet.request.PayloadValor;
import com.fishcount.common.model.classes.gerencianet.response.PayloadCobrancaResponse;
import com.fishcount.common.model.dto.financeiro.pix.CobrancaPixDTO;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import com.fishcount.common.model.entity.financeiro.pix.LocationPix;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
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
        cobrancaPix.setPagamentoParcela(parcela);

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
                .getSeconds();

        return Integer.parseInt(Long.toString(segundosMes));
    }
}
