
package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomCobrancaPixRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.CobrancaPix;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Lucas Martins
 */
@Repository
public class CobrancaPixRepositoryImpl extends GenericImpl<CobrancaPix, Integer> implements CustomCobrancaPixRepository {

}
