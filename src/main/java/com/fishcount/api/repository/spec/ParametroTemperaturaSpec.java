package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.ParametroTemperatura;
import com.fishcount.common.utils.ListUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ParametroTemperaturaSpec {

    private final static String FIELD_TEMPERATURA_MAX = "temperaturaMaxima";

    private final static String FIELD_TEMPERATURA_MIN = "temperaturaMinima";


    public static Specification<ParametroTemperatura> byTemperatura(Integer temperatura) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_TEMPERATURA_MIN), temperatura));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_TEMPERATURA_MAX), temperatura));

            return query
                    .where(ListUtil.toArray(predicates))
                    .getRestriction();
        };
    }
}
