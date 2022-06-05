package com.fishcount.api.service.impl;

import com.fishcount.api.client.gerencianet.pix.cobranca.CobrancaPix;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.api.validators.PagamentoValidator;
import com.fishcount.common.model.classes.gerencianet.PayloadCalendario;
import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;
import com.fishcount.common.model.classes.gerencianet.PayloadDevedor;
import com.fishcount.common.model.classes.gerencianet.PayloadValor;
import com.fishcount.common.model.dto.PagamentoDTO;
import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.entity.TituloParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import com.fishcount.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl extends AbstractServiceImpl<Pagamento, Integer, PagamentoDTO> implements PagamentoService {

    private final PagamentoValidator pagamentoValidator = new PagamentoValidator();
    
    private final CobrancaPix cobrancaPix;

    @Override
    public Pagamento incluir(TituloParcela tituloParcela, Pagamento pagamento) {
        onPrepareInsert(tituloParcela, pagamento);

        pagamentoValidator.validateInsert(pagamento);

        return getRepository().save(pagamento);
    }

    private void onPrepareInsert(TituloParcela tituloParcela, Pagamento pagamento) {
        pagamento.setDataAlteracao(DateUtil.getDate());
        pagamento.setDataInclusao(DateUtil.getDate());
        pagamento.setDataVencimento(tituloParcela.getDataVencimento());
        pagamento.setValor(tituloParcela.getValor());
        pagamento.setUsuario(tituloParcela.getTitulo().getUsuario());
        pagamento.setTipoPagamento(EnumTipoPagamento.PIX);
        pagamento.setStatusPagamento(EnumStatusPagamento.ABERTO);
        pagamento.setSaldo(tituloParcela.getSaldo());
        
        PayloadCobranca cobranca = new PayloadCobranca();
        cobranca.setSolicitacaoPagador("Teste n1");
        cobranca.setChave("Minhachave");
        
        PayloadCalendario calendario = new PayloadCalendario(DateUtil.getDate(), 2592);
        cobranca.setCalendario(calendario);
        
        PayloadDevedor devedor = new PayloadDevedor("Teste Lucas", "10574732942", null);
        cobranca.setDevedor(devedor);
        
        PayloadValor valor = new PayloadValor("1.05");
        cobranca.setValor(valor);
        
        cobrancaPix.criarCobrancaImediata(cobranca);

        
    }

}
