package com.fishcount.api.service.impl;

import com.fishcount.api.service.TelefoneService;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.NumericUtil;
import com.fishcount.common.utils.Utils;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;

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
    public Telefone incluir(Telefone telefone) {
        telefoneValidator.validateInsert(telefone);
        
        onPrepareInsert(telefone);
        
        return getRepository().save(telefone);
    }

    private void onPrepareInsert(Telefone telefone) {
        telefone.setAtivo(true);
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setDataInclusao(DateUtil.getDate());
    }

}
