package com.fishcount.api.service.impl;

import com.fishcount.api.repository.TelefoneRepository;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.DateUtil;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class TelefoneServiceImpl extends AbstractServiceImpl<Telefone, Integer, TelefoneDTO> implements TelefoneService {

    private final TelefoneValidator telefoneValidator = new TelefoneValidator();
    
    @Override
    public Telefone incluir(Integer idUsuario, Telefone telefone) {
        onPrepareInsert(telefone);
        
        telefoneValidator.validateInsertOrUpdate(telefone);
        
        return getRepository().save(telefone);
    }

    private void onPrepareInsert(Telefone telefone) {
        telefone.setAtivo(true);
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setDataInclusao(DateUtil.getDate());
    }

    @Override
    public void editar(Integer idUsuario, Integer idTelefone, Telefone telefone) {
        onPrepareUpdate(idUsuario, idTelefone, telefone);
        
        telefoneValidator.validateInsertOrUpdate(telefone);
        
        getRepository().save(telefone);
    }

    @Override
    public List<Telefone> listarAtivos(Integer idUsuario) {
        final Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        
        return getRepository(TelefoneRepository.class).findAllAtivosByUsuario(usuario);
    }

    @Override
    public void inativar(Integer idUsuario, Integer idTelefone) {
        Telefone telefone = findAndValidate(idTelefone);
        
        onPrepareDelete(telefone);
        
        telefoneValidator.validateDelete(telefone);
        
        getRepository().save(telefone);
    }

    private void onPrepareUpdate(Integer idUsuario, Integer idTelefone, Telefone telefone) {
        final Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        
        final Telefone managedTelefone = findAndValidate(idTelefone);
        
        telefone.setId(managedTelefone.getId());
        telefone.setAtivo(true);
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setUsuario(usuario);
    }

    private void onPrepareDelete(Telefone telefone) {
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setAtivo(false);
    }

}
