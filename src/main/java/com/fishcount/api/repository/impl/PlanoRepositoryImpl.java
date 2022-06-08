
package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPlanoRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Plano;
import org.springframework.stereotype.Repository;

@Repository
public class PlanoRepositoryImpl extends GenericImpl<Plano, Integer> implements CustomPlanoRepository {

}
