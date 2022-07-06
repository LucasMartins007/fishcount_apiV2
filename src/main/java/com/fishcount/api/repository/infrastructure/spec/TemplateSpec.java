package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.Template;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateSpec {

    public static final String FIELD_TIPO_ENVIO_EMAIL = "tipoEnvioEmail";

    public static Specification<Template> byTipoEnvioEmail(EnumTipoEnvioEmail tipoEnvioEmail) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_TIPO_ENVIO_EMAIL), tipoEnvioEmail);
    }
}
