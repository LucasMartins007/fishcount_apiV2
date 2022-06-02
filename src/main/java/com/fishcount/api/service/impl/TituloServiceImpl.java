package com.fishcount.api.service.impl;

import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.api.service.TituloService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.TituloValidator;
import com.fishcount.common.model.dto.TituloDTO;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.TituloParcela;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.utils.DateUtil;
import java.util.Calendar;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public class TituloServiceImpl extends AbstractServiceImpl<Titulo, Integer, TituloDTO> implements TituloService {

    private final TituloValidator tituloValidator = new TituloValidator();

    @Override
    public Titulo incluir(Integer idUsuario, Titulo titulo) {
        onPrepareInsert(titulo, idUsuario);

        tituloValidator.validateInsert(titulo);

        return getRepository().save(titulo);
    }

    private void onPrepareInsert(Titulo titulo, Integer idUsuario) {
        Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);

        titulo.setUsuario(usuario);
        titulo.setDataInclusao(DateUtil.getDate());
        titulo.setTipoTitulo(EnumTipoTitulo.ENTRADA);
        titulo.setDataVencimento(DateUtil.add(Calendar.MONTH, 1));

        Integer qtdeParcelas = titulo.getQtdeParcelas();
        for (int i = 0; i < qtdeParcelas; i++) {
            TituloParcela tituloParcela = new TituloParcela();
            
            getService(TituloParcelaService.class).incluir(titulo, tituloParcela);
        }
    }

}
