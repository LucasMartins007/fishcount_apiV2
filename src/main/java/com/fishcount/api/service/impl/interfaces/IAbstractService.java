package com.fishcount.api.service.impl.interfaces;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.pattern.AbstractEntity;
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
 * @param <DTO>
 */
public interface IAbstractService<E extends AbstractEntity<?>, I, DTO extends AbstractDTO<?>>  {
    
    JpaRepository<E, I> getRepository();

    JpaSpecificationExecutor<E> getSpecRepository();

    DTO findById(I id);

    List<DTO> findAll();

    Page<DTO> findAll(Pageable pageable);

    E findAndValidate(I id);
    
//    Page<DTO> findAll(Pageable pageable, Map filters, ExampleMatcher.StringMatcher matchFilter);
//
//    Page<DTO> findAll(Pageable pageable, Map filters);
}
