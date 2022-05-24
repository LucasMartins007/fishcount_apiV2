package com.fishcount.api.service.impl;

import com.fishcount.api.service.TelefoneService;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;
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
        telefoneValidator.validateInsert(telefone);
        
        onPrepareInsert(telefone);
        
        return getRepository().save(telefone);
    }

    private void onPrepareInsert(Telefone telefone) {
        telefone.setAtivo(true);
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setDataInclusao(DateUtil.getDate());
    }

    @Override
    public void editar(Integer idUsuario, Telefone telefone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Telefone> listar(Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void inativar(Integer idUsuario, Telefone telefone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
