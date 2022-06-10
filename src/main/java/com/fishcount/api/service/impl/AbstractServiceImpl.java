/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fishcount.api.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.fishcount.api.converter.Converter;
import com.fishcount.api.infrastructure.context.IContext;
import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.pattern.AbstractEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 *
 * @author lucas
 * @param <E>
 * @param <I>
 * @param <DTO>
 */
public abstract class AbstractServiceImpl<E extends AbstractEntity<?>, I extends Serializable, DTO extends AbstractDTO<?>>
        implements IAbstractService<E, I, DTO> {

    private final Class<E> entityClass;
    private final Class<DTO> dtoClass;

    public AbstractServiceImpl() {
        Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        entityClass = (Class<E>) actualTypeArguments[0];
        dtoClass = (Class<DTO>) actualTypeArguments[2];
    }

    private IContext getContext() {
        return IContext.context();
    }

    @Override
    public JpaRepository<E, I> getRepository() {
        return getContext().getRepositoryFromClass(entityClass);
    }

    public <R extends Repository> R getRepository(Class<R> classRespository) {
        return getContext().getBean(classRespository);
    }

    public <R extends IAbstractService> R getService(Class<R> classService) {
        return getContext().getBean(classService);
    }

    @Override
    public JpaSpecificationExecutor<E> getSpecRepository() {
        return getContext().getSpecRepositoryFromClass(entityClass);
    }

    @Override
    public E findAndValidate(I id) {
        if (id == null) {
            throw new FcRuntimeException(EnumFcInfraException.NULL_POINTER_EXCEPTION);
        }

        return getRepository()
                .findById(id)
                .orElseThrow(() -> new FcRuntimeException(EnumFcInfraException.ENTITY_NOT_FOUND, entityClass.getSimpleName(), id));
    }

    @Override
    public DTO findById(I id) {
        final E entidade = findAndValidate(id);

        return converterEntityToDTO(entidade);
    }

    protected DTO converterEntityToDTO(E entidade) {
        return Converter.converterEntityParaDTO(entidade, dtoClass);
    }

    private E converterDTOToEntity(DTO dto) {
        return Converter.converterDTOParaEntity(dto, entityClass);
    }

    private Sort sortBy() {
        return Sort.unsorted();
    }

    @Override
    public List<DTO> findAll() {
        final List<E> entidades = getRepository().findAll();

        return entidades.stream()
                .map(this::converterEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DTO> findAll(Pageable pageable) {
        Sort sort = pageable.getSort();

        if (sort == null) {
            sort = sortBy();
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }

        final Page<E> entidades = getRepository().findAll(pageable);

        return entidades.map(this::converterEntityToDTO);
    }

}
