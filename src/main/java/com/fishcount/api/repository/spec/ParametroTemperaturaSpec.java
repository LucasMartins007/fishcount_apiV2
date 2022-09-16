package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.ParametroTemperatura;
import com.fishcount.common.utils.ListUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.servlet.tags.Param;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParametroTemperaturaSpec {

    private final static String FIELD_TEMPERATURA_MAX = "temperaturaMaxima";

    private final static String FIELD_TEMPERATURA_MIN = "temperaturaMinima";


    public static Specification<ParametroTemperatura> temperaturaMaximaIsGreaterThan(BigDecimal temperatura){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_TEMPERATURA_MAX), temperatura);
    }

    public static Specification<ParametroTemperatura> temperaturaMinimaIsLesserThan(BigDecimal temperatura){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_TEMPERATURA_MIN), temperatura);
    }
}
