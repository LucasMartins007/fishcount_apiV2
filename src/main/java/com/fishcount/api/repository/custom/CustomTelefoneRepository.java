package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomTelefoneRepository {

    Telefone findByDescricao(String descricao);
    
    Telefone findByUsuarioAndTipo(Usuario usuario, EnumTipoTelefone tipoTelefone);
    
    List<Telefone> findAllAtivosByUsuario(Usuario usuario);
}
