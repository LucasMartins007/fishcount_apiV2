package com.fishcount.api.service.impl;

import com.fishcount.api.repository.CobrancaPixRepository;
import com.fishcount.api.repository.PagamentoParcelaRepository;
import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.PagamentoParcelaValidator;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoParcelaServiceImpl
        extends AbstractServiceImpl<PagamentoParcela, Integer, PagamentoParcelaDTO>
        implements PagamentoParcelaService {

    private final PagamentoParcelaValidator pagamentoParcelaValidator;

    @Override
    public List<PagamentoParcela> incluirParcelas(Pagamento pagamento) {
        List<PagamentoParcela> parcelas = gerarParcelas(pagamento);

        getRepository().saveAll(parcelas);

        parcelas.forEach(this::onAfterInsert);

        getService(CobrancaPixService.class).gerarRegistoCobrancaPix(ListUtil.first(parcelas));

        return parcelas;
    }

    private void onAfterInsert(PagamentoParcela parcela) {
        getService(TituloParcelaService.class).gerarTitulosParcelasByPagamentoParcela(parcela);
    }

    private List<PagamentoParcela> gerarParcelas(Pagamento pagamento) {
        final Integer qtdeParcelas = pagamento.getQtdeParcelas();
        final BigDecimal valorParcelas = BigDecimalUtil.truncBig(pagamento.getValor().divide(BigDecimal.valueOf(qtdeParcelas), RoundingMode.HALF_EVEN), 2);

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
        parcela.setDataAtualizacao(DateUtil.getDate());
        parcela.setDataInclusao(DateUtil.getDate());
        parcela.setDataVencimento(DateUtil.add(Calendar.MONTH, aumentoMesesVencimento));
        parcela.setValor(valorParcelas);
        parcela.setSaldo(valorParcelas);

        pagamentoParcelaValidator.validateInsert(parcela);
    }

    @Override
    public List<PagamentoParcela> listarParcelas(Integer pessoaId, Integer pagamentoId, EnumStatusPagamento statusPagamento) {
        if (Utils.isEmpty(statusPagamento)){
            return getRepository(PagamentoParcelaRepository.class).findAllByPessoaAndPagamento(pessoaId, pagamentoId);
        }
        return getRepository(PagamentoParcelaRepository.class).findAllByPessoaAndPagamentoAndStatus(pessoaId, pagamentoId, statusPagamento);
    }

    @Override
    public PagamentoParcela consultarParcela(Integer pessoaId, Integer pagamentoId, Integer parcelaId) {
        return findAndValidate(parcelaId);
    }

    @Override
    public QRCodePix gerarQRCodeByParcela(Integer pessoaId, Integer pagamentoId) {
        final CobrancaPix cobrancaPix = getRepository(CobrancaPixRepository.class).findByPagamentoParcela(pagamentoId);

        return getService(LocationPixService.class).gerarQrCodePorLocation(pessoaId, cobrancaPix.getLocation().getIdLocation());
    }

}
