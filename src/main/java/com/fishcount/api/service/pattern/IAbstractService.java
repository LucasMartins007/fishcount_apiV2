package com.fishcount.api.service.pattern;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 *
 * @author lucas
 * @param <E>
 * @param <I>
 * @param <D>
 */
public interface IAbstractService<E extends AbstractEntity<?>, I, D extends AbstractDTO<?>>  {
    
    JpaRepository<E, I> getRepository();

    JpaSpecificationExecutor<E> getSpecRepository();

    D findById(I id);

    List<D> findAll();

    Page<D> findAll(Pageable pageable);

    E findAndValidate(I id);

}
