package com.fishcount.api.service.impl;

import com.fishcount.api.repository.PagamentoRepository;
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
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.DateUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
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

        getService(PagamentoParcelaService.class).incluirParcelas(pagamento);

        return pagamento;
    }

    @Override
    public List<Pagamento> listarPagamentos(Integer idUsuario) {
        final Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);

        return getRepository(PagamentoRepository.class).findAllPagamentoByUsuario(usuario);
    }

    @Override
    public Pagamento consultarCobranca(Integer idUsuario, Integer id) {
        final Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);

        return getRepository(PagamentoRepository.class).findPagamentoByUsuarioAndId(usuario, id);
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

        pagamentoValidator.validateInsert(pagamento);

        getRepository().save(pagamento);
    }

}
