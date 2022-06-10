package com.fishcount.api.service.impl;

import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.api.validators.PagamentoParcelaValidator;
import com.fishcount.common.model.dto.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.DateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoParcelaServiceImpl
        extends AbstractServiceImpl<PagamentoParcela, Integer, PagamentoParcelaDTO>
        implements PagamentoParcelaService {

    private final PagamentoParcelaValidator pagamentoParcelaValidator = new PagamentoParcelaValidator();

    @Override
    public List<PagamentoParcela> incluirParcelas(Pagamento pagamento) {
        List<PagamentoParcela> parcelas = gerarParcelas(pagamento);

        getRepository().saveAll(parcelas);

        parcelas.forEach(parcela -> {
            getService(TituloParcelaService.class).gerarTitulosParcelasByPagamentoParcela(parcela);
            getService(CobrancaPixService.class).gerarRegistoCobrancaPix(parcela);
        });

        return parcelas;
    }

    private List<PagamentoParcela> gerarParcelas(Pagamento pagamento) {
        final Integer qtdeParcelas = pagamento.getQtdeParcelas();
        final BigDecimal valorParcelas = BigDecimalUtil.truncBig(pagamento.getValor().divide(BigDecimal.valueOf(qtdeParcelas)), 2);

        final List<PagamentoParcela> parcelas = new ArrayList<>();
        for (int i = 0; i < qtdeParcelas; i++) {
            final PagamentoParcela parcela = new PagamentoParcela();
            final Integer aumentoMesesVencimento = i + 1;

            parcela.setPagamento(pagamento);
            parcela.setStatusPagamento(pagamento.getStatusPagamento());
            parcela.setTipoPagamento(pagamento.getTipoPagamento());
            parcela.setAcrescimo(pagamento.getAcrescimo());
            parcela.setDesconto(pagamento.getDesconto());
            onPrepareInsert(parcela, valorParcelas, aumentoMesesVencimento);

            parcelas.add(parcela);
        }
        return parcelas;
    }

    private void onPrepareInsert(final PagamentoParcela parcela, final BigDecimal valorParcelas, final Integer aumentoMesesVencimento) {
        parcela.setDataAlteracao(DateUtil.getDate());
        parcela.setDataInclusao(DateUtil.getDate());
        parcela.setDataVencimento(DateUtil.add(Calendar.MONTH, aumentoMesesVencimento));
        parcela.setValor(valorParcelas);
        parcela.setSaldo(valorParcelas);

        pagamentoParcelaValidator.validateInsert(parcela);
    }

}
