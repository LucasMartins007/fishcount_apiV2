package com.fishcount.api.service.impl;

import com.fishcount.api.repository.PagamentoParcelaRepository;
import com.fishcount.api.repository.PagamentoRepository;
import com.fishcount.api.service.*;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.PagamentoValidator;
import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl extends AbstractServiceImpl<Pagamento, Integer, PagamentoDTO> implements PagamentoService {

    private final PagamentoValidator pagamentoValidator;

    @Override
    public Pagamento incluir(Pagamento pagamento, Integer idUsuario) {
        onPrepareInsert(idUsuario, pagamento);

        getRepository().save(pagamento);

        getService(PagamentoParcelaService.class).incluirParcelas(pagamento);

        return pagamento;
    }

    @Override
    public List<Pagamento> listarPagamentos(Integer idPessoa) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);

        return getRepository(PagamentoRepository.class).findAllPagamentoByUsuario(pessoa);
    }

    @Override
    public List<PagamentoParcela> listarParcelas(Integer idUsuario, EnumStatusPagamento statusPagamento){
        return getRepository(PagamentoParcelaRepository.class).findAllByUsuarioAndStatus(idUsuario, statusPagamento);

    }

    @Override
    public Pagamento consultarCobranca(Integer idPessoa, Integer id) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);

        return getRepository(PagamentoRepository.class).findPagamentoByUsuarioAndId(pessoa, id);
    }

    private void onPrepareInsert(Integer idPessoa, Pagamento pagamento) {
        final Plano plano = getService(PlanoService.class).findAndValidate(pagamento.getPlano().getId());
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        final Titulo titulo = getService(TituloService.class).gerarTituloByPlano(pessoa, plano);

        pagamento.setPessoa(pessoa);
        pagamento.setTitulo(titulo);

        gerarPagamentoAnalise(pagamento, plano);
    }

    private void gerarPagamentoAnalise(final Pagamento pagamento, final Plano plano) {
        pagamento.setDataInclusao(DateUtil.getDate());
        pagamento.setDataAlteracao(DateUtil.getDate());
        pagamento.setDataVencimento(DateUtil.add(Calendar.MONTH, 1));
        pagamento.setStatusPagamento(EnumStatusPagamento.ANALISE);
        pagamento.setSaldo(BigDecimalUtil.truncBig(BigDecimal.ZERO, 1));
        pagamento.setValor(plano.getValorMinimo());
        pagamento.setQtdeParcelas(plano.getQtdeParcela()== null ? pagamento.getQtdeParcelas() : plano.getQtdeParcela());

        pagamentoValidator.validateInsert(pagamento);

        getRepository().save(pagamento);
    }

}
