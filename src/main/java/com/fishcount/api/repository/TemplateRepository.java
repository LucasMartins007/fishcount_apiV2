package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTemplateRepository;
import com.fishcount.common.model.entity.Template;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer>, JpaSpecificationExecutor<Template>, CustomTemplateRepository {

    @Override
    Template findByTipoEnvioEmail(EnumTipoEnvioEmail tipoEnvioEmail);
}