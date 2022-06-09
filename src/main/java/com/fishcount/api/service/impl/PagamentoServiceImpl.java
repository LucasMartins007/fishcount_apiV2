package com.fishcount.api.service.impl;

import com.fishcount.api.client.gerencianet.pix.cobranca.CobrancaPix;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.api.service.PlanoService;
import com.fishcount.api.service.TituloService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.PagamentoValidator;
import com.fishcount.common.model.dto.PagamentoDTO;
import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.entity.Plano;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.TituloParcela;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.DateUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl extends AbstractServiceImpl<Pagamento, Integer, PagamentoDTO> implements PagamentoService {

    private final PagamentoValidator pagamentoValidator = new PagamentoValidator();

    @Override
    public Pagamento incluir(Pagamento pagamento, Integer idUsuario) {
        onPrepareInsert(idUsuario, pagamento);

        getRepository().save(pagamento);

        onAfterInsert(pagamento);
        
        return pagamento;
    }

    private void onPrepareInsert(Integer idUsuario, Pagamento pagamento) {
        final Plano plano = getService(PlanoService.class).findAndValidate(pagamento.getPlano().getId());
        final Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        final Titulo titulo = getService(TituloService.class).gerarTituloByPlano(usuario, plano);

        pagamento.setUsuario(usuario);
        pagamento.setTitulo(titulo);

        gerarPagamentoAnalise(pagamento, plano);
    }

    private void gerarPagamentoAnalise(final Pagamento pagamento, final Plano plano) {
        pagamento.setDataInclusao(DateUtil.getDate());
        pagamento.setDataAlteracao(DateUtil.getDate());
        pagamento.setDataVencimento(DateUtil.add(Calendar.MONTH, 1));
        pagamento.setStatusPagamento(EnumStatusPagamento.ANALISE);
        pagamento.setSaldo(BigDecimal.ZERO);
        pagamento.setValor(plano.getValorMinimo());
        pagamento.setQtdeParcelas(plano.getNumParcelas() == null ? pagamento.getQtdeParcelas() : plano.getNumParcelas());

        getRepository().save(pagamento);
    }

    @Override
    public Pagamento incluir(TituloParcela tituloParcela, Pagamento pagamento) {

        return getRepository().save(pagamento);
    }

    private void onAfterInsert(Pagamento pagamento) {
        getService(PagamentoParcelaService.class).incluirParcelas(pagamento);
    }
}
