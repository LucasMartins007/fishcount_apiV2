package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTelefoneRepository;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer>, JpaSpecificationExecutor<Telefone>, CustomTelefoneRepository {

    @Override
    Telefone findByDescricao(String descricao);

    @Override
    Telefone findByUsuarioAndTipo(Usuario usuario, EnumTipoTelefone tipoTelefone);
    
    @Override
    List<Telefone> findAllAtivosByUsuario(Usuario usuario);
    
}
