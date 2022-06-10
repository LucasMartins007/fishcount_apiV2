package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTituloRepository;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface TituloRepository extends JpaRepository<Titulo, Integer>, JpaSpecificationExecutor<Titulo>, CustomTituloRepository {

    @Override
    Titulo findByStatusAndUsuario(EnumStatusTitulo statusTitulo, Usuario usuario);
}