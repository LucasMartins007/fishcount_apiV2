package com.fishcount.api.service.impl;

import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.LoteValidator;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
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

    private final LoteValidator loteValidator = new LoteValidator();

    @Override
    public Lote incluir(Integer idUsuario, Lote lote) {
        onPrepareInsertOrUpdate(idUsuario, lote);

        loteValidator.validateInsertOrUpdate(lote);

        return getRepository().save(lote);
    }

    @Override
    public void editar(Integer idUsuario, Lote lote) {
        onPrepareInsertOrUpdate(idUsuario, lote);

        loteValidator.validateInsertOrUpdate(lote);

        getRepository().save(lote);
    }

    @Override
    public List<Lote> listarFromUsuario(Integer idUsuario) {
        Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);

        return getRepository(LoteRepository.class).findAllByUsuario(usuario);
    }

    private void onPrepareInsertOrUpdate(Integer idUsuario, Lote lote) {
        if (Utils.isNotEmpty(lote.getId())){
            Lote managedLote = getService(LoteService.class).findAndValidate(lote.getId());
            lote.setTanques(managedLote.getTanques());
        }
        
        Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        lote.setDescricao(lote.getDescricao().toLowerCase());
        lote.setDataInclusao(DateUtil.getDate());
        lote.setDataAtualizacao(DateUtil.getDate());
        lote.setUsuario(usuario);
    }

}
