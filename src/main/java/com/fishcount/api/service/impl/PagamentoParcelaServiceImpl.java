package com.fishcount.api.service.impl;

import com.fishcount.api.repository.CobrancaPixRepository;
import com.fishcount.api.repository.PagamentoParcelaRepository;
import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.api.validators.PagamentoParcelaValidator;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.model.enums.EnumStatusPagamento;
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

    @Override
    public List<PagamentoParcela> listarParcelas(Integer idUsuario, Integer idPagamento, EnumStatusPagamento statusPagamento) {
        return getRepository(PagamentoParcelaRepository.class).findAllByUsuarioAndPagamentoAndStatus(idUsuario, idPagamento, statusPagamento);
    }

    @Override
    public PagamentoParcela consultarParcela(Integer idUsuario, Integer idPagamento, Integer idParcela) {
        return findAndValidate(idParcela);
    }

    @Override
    public QRCodePix gerarQRCodeByParcela(Integer idUsuario, Integer idParcela) {
        final CobrancaPix cobrancaPix = getRepository(CobrancaPixRepository.class).findByPagamentoParcela(idParcela);

        return getService(LocationPixService.class).gerarQrCode(idUsuario, cobrancaPix.getLocation().getIdLocation());
    }

}
