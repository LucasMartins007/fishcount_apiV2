package com.fishcount.api.service.impl;

import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.LoteValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class LoteServiceImpl extends AbstractServiceImpl<Lote, Integer, LoteDTO> implements LoteService {

    private final LoteValidator loteValidator;

    private final LoteRepository  loteRepository;

    @Override
    public Lote incluir(Integer pessoaId, Lote lote) {
        onPrepareInsertOrUpdate(pessoaId, null, lote);

        loteValidator.validateInsertOrUpdate(lote);

        return loteRepository.save(lote);
    }

    @Override
    public void editar(Integer pessoaId, Integer loteId, Lote lote) {
        onPrepareInsertOrUpdate(pessoaId, loteId, lote);

        loteValidator.validateInsertOrUpdate(lote);

        loteRepository.save(lote);
    }

    @Override
    public List<Lote> listarFromPessoa(Integer pessoaId, String orderBy) {
        final List<Lote> lotesAtivos = resolverListaLotes(orderBy, pessoaId);

        return ListUtil.stream(lotesAtivos)
                .peek(lote -> {
                    final List<Tanque> tanquesAtivos = ListUtil.stream(lote.getTanques())
                            .filter(Tanque::isAtivo)
                            .collect(Collectors.toList());
                    lote.setTanques(tanquesAtivos);
                })
                .collect(Collectors.toList());
    }

    private List<Lote> resolverListaLotes(String orderBy, Integer pessoaId) {
        if (Utils.isNotEmpty(orderBy)) {
            return loteRepository.findAllAtivosByPessoaOrderBy(pessoaId, orderBy);
        }
        return loteRepository.findAllAtivosByPessoa(pessoaId);
    }

    @Override
    public void onPrepareInsertOrUpdate(Integer pessoaId, Integer loteId, Lote lote) {
        if (Utils.isNotEmpty(loteId)) {
            final Lote managedLote = findAndValidate(loteId);
            lote.setTanques(managedLote.getTanques());
            lote.setDataInclusao(managedLote.getDataInclusao());
        }

        Pessoa pessoa = getService(PessoaService.class).findAndValidate(pessoaId);
        lote.setDescricao(lote.getDescricao().toLowerCase());
        lote.setPessoa(pessoa);
        lote.setAtivo(true);
    }

    @Override
    public void inativar(Integer pessoaId, Integer loteId) {
        final Lote lote = findAndValidate(loteId);
        if (!Utils.equals(lote.getPessoa().getId(), pessoaId)) {
            throw new FcRuntimeException(EnumFcDomainException.LOTE_NAO_PERTENCE_PESSOA, loteId, pessoaId);
        }
        lote.setAtivo(false);
        lote.setDataAtualizacao(DateUtil.getDate());

        loteRepository.save(lote);
    }

    @Override
    public void validarLotes(Pessoa pessoa) {
        ListUtil.stream(pessoa.getLotes())
                .forEach(lote -> {
                    lote.setPessoa(pessoa);
                    loteValidator.validateInsertOrUpdate(lote);

                    if (Utils.isNotEmpty(pessoa.getId())) {
                        onPrepareInsertOrUpdate(pessoa.getId(), null, lote);
                    }
                });
    }

}
