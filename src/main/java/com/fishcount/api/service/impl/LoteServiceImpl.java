package com.fishcount.api.service.impl;

import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.validators.LoteValidator;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class LoteServiceImpl extends AbstractServiceImpl<Lote, Integer, LoteDTO> implements LoteService {

    private final LoteValidator loteValidator;

    @Override
    public Lote incluir(Integer idPessoa, Lote lote) {
        onPrepareInsertOrUpdate(idPessoa, lote);

        loteValidator.validateInsertOrUpdate(lote);

        return getRepository().save(lote);
    }

    @Override
    public void editar(Integer idPessoa, Lote lote) {
        onPrepareInsertOrUpdate(idPessoa, lote);

        loteValidator.validateInsertOrUpdate(lote);

        getRepository().save(lote);
    }

    @Override
    public List<Lote> listarFromPessoa(Integer idPessoa) {
        Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);

        return getRepository(LoteRepository.class).findAllByPessoa(pessoa);
    }

    private void onPrepareInsertOrUpdate(Integer idPessoa, Lote lote) {
        if (Utils.isNotEmpty(lote.getId())){
            Lote managedLote = getService(LoteService.class).findAndValidate(lote.getId());
            lote.setTanques(managedLote.getTanques());
        }
        
        Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        lote.setDescricao(lote.getDescricao().toLowerCase());
        lote.setDataInclusao(DateUtil.getDate());
        lote.setDataAtualizacao(DateUtil.getDate());
        lote.setPessoa(pessoa);
    }

}
