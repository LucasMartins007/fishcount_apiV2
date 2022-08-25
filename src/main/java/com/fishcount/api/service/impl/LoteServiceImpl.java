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
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class LoteServiceImpl extends AbstractServiceImpl<Lote, Integer, LoteDTO> implements LoteService {

    private final LoteValidator loteValidator;

    @Override
    public Lote incluir(Integer pessoaId, Lote lote) {
        onPrepareInsertOrUpdate(pessoaId, null, lote);

        loteValidator.validateInsertOrUpdate(lote);

        return getRepository().save(lote);
    }

    @Override
    public void editar(Integer pessoaId, Integer loteId, Lote lote) {
        onPrepareInsertOrUpdate(pessoaId, loteId, lote);

        loteValidator.validateInsertOrUpdate(lote);

        getRepository().save(lote);
    }

    @Override
    public List<Lote> listarFromPessoa(Integer pessoaId) {
        Pessoa pessoa = getService(PessoaService.class).findAndValidate(pessoaId);

        return getRepository(LoteRepository.class).findAllAtivosByPessoa(pessoa);
    }

    @Override
    public void onPrepareInsertOrUpdate(Integer pessoaId, Integer loteId, Lote lote) {
        if (Utils.isNotEmpty(loteId)) {
            final Lote managedLote = getService(LoteService.class).findAndValidate(loteId);
            lote.setTanques(managedLote.getTanques());
            lote.setDataInclusao(managedLote.getDataInclusao());
        }

        Pessoa pessoa = getService(PessoaService.class).findAndValidate(pessoaId);
        lote.setDescricao(lote.getDescricao().toLowerCase());
        lote.setPessoa(pessoa);
    }

    @Override
    public void inativar(Integer pessoaId, Integer loteId) {
        final Lote lote = findAndValidate(loteId);
        if (!Utils.equals(lote.getPessoa().getId(), pessoaId)) {
            throw new FcRuntimeException(EnumFcDomainException.LOTE_NAO_PERTENCE_PESSOA, loteId, pessoaId);
        }
        lote.setAtivo(false);
        lote.setDataAtualizacao(DateUtil.getDate());

        getRepository().save(lote);
    }

}
