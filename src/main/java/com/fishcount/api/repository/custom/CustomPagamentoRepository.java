package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomPagamentoRepository {

    List<Pagamento> findAllPagamentoByUsuario(Usuario usuario);
    
    Pagamento findPagamentoByUsuarioAndId(Usuario usuario, Integer id);
    
}
