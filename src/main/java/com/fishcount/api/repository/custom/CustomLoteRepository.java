package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomLoteRepository {

    Lote findByDescricao(String descricao);

    List<Lote> findAllByUsuario(Usuario usuario);
}
