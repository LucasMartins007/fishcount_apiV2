package com.fishcount.api.repository;

import com.fishcount.common.model.entity.financeiro.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.fishcount.api.repository.custom.CustomPagamentoRepository;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento>, CustomPagamentoRepository {

    @Override
    public List<Pagamento> findAllPagamentoByUsuario(Usuario usuario);

    @Override
    public Pagamento findPagamentoByUsuarioAndId(Usuario usuario, Integer id);
    
}
