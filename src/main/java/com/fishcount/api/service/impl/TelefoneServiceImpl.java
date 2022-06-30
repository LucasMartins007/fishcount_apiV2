package com.fishcount.api.service.impl;

import com.fishcount.api.repository.TelefoneRepository;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class TelefoneServiceImpl extends AbstractServiceImpl<Telefone, Integer, TelefoneDTO> implements TelefoneService {

    private final TelefoneValidator telefoneValidator;
    
    @Override
    public Telefone incluir(Integer idPessoa, Telefone telefone) {
        onPrepareInsert(telefone, idPessoa);
        
        telefoneValidator.validateInsertOrUpdate(telefone);
        
        return getRepository().save(telefone);
    }

    private void onPrepareInsert(Telefone telefone, Integer idPessoa) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);

        telefone.setAtivo(true);
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setDataInclusao(DateUtil.getDate());
        telefone.setPessoa(pessoa);
    }

    @Override
    public void editar(Integer idPessoa, Integer idTelefone, Telefone telefone) {
        onPrepareUpdate(idPessoa, idTelefone, telefone);
        
        telefoneValidator.validateInsertOrUpdate(telefone);
        
        getRepository().save(telefone);
    }

    @Override
    public List<Telefone> listarAtivos(Integer idPessoa) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        
        return getRepository(TelefoneRepository.class).findAllAtivosByPessoa(pessoa);
    }

    @Override
    public void inativar(Integer idUsuario, Integer idTelefone) {
        Telefone telefone = findAndValidate(idTelefone);
        
        onPrepareDelete(telefone);
        
        telefoneValidator.validateDelete(telefone);
        
        getRepository().save(telefone);
    }

    private void onPrepareUpdate(Integer idPessoa, Integer idTelefone, Telefone telefone) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        
        final Telefone managedTelefone = findAndValidate(idTelefone);
        
        telefone.setId(managedTelefone.getId());
        telefone.setAtivo(true);
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setPessoa(pessoa);
    }

    private void onPrepareDelete(Telefone telefone) {
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setAtivo(false);
    }

}
