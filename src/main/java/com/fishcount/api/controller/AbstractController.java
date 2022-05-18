package com.fishcount.api.controller;

import com.fishcount.api.converter.Converter;
import com.fishcount.api.infrastructure.context.IContext;
import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.dto.pattern.converter.DefaultDTO;
import com.fishcount.common.model.pattern.AbstractEntity;
import com.fishcount.common.utils.LoggerUtil;
import com.fishcount.common.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author lucas
 * @param <E>
 */
public abstract class AbstractController<E extends IAbstractService> {

    @Autowired
    private HttpServletRequest request;
    
    private Class<E> serviceClass;

    public AbstractController() {
        this.serviceClass = resolverServiceClass(this.getClass());
    }

    protected E getService() {
        return getContext().getBean(serviceClass);
    }

    protected ResponseEntity okResponse() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity okResponse(Object body) {
        return ResponseEntity.ok(body);
    }

    protected <E extends AbstractEntity, DTO extends AbstractDTO> ResponseEntity okResponsePage(Page<E> bodyPageCollection, Class<DTO> classDTO) {
        if (!bodyPageCollection.isEmpty()) {
            return ResponseEntity.ok(bodyPageCollection.map(item -> converterEntityParaDTO(item, classDTO)));
        }
        return createNoContentResponse();
    }

    protected <D extends AbstractDTO> ResponseEntity defaultResponse(List<? extends AbstractEntity> list, Class<D> clazzDto) {
        final List<D> dtoList = Converter.converterEntityParaDTO(list, clazzDto);
        return okResponse(new DefaultDTO(dtoList));
    }

    protected ResponseEntity createNoContentResponse() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity createBadRequestResponse() {
        return ResponseEntity.badRequest().build();
    }

    protected Integer getParamValueAsInteger(final String pathVariable) {
        return RequestUtil.getParamValueAsInteger(request, pathVariable);
    }

    protected <D extends AbstractDTO<?>, E extends AbstractEntity> E converterDTOParaEntity(D dto, Class<E> clazzEntity) {
        return Converter.converterDTOParaEntity(dto, clazzEntity);
    }

    protected <D extends AbstractDTO<?>, E extends AbstractEntity> List<E> converterDTOParaEntity(List<D> dtos, Class<E> clazzEntity) {
        return Converter.converterDTOParaEntity(dtos, clazzEntity);
    }

    protected <D extends AbstractDTO<?>, E extends AbstractEntity> D converterEntityParaDTO(E entity, Class<D> clazzDto) {
        return Converter.converterEntityParaDTO(entity, clazzDto);
    }

    protected <D extends AbstractDTO<?>, E extends AbstractEntity> List<D> converterEntityParaDTO(List<E> entitys, Class<D> clazzDto) {
        return Converter.converterEntityParaDTO(entitys, clazzDto);
    }

    private Class<E> resolverServiceClass(Class<?> serviceClass) {
        try {
            return (Class<E>) ((ParameterizedType) serviceClass.getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Exception e) {
            LoggerUtil.getLogger(this.getClass()).error(e.getMessage());
        }
        return null;
    }

    private IContext getContext() {
        return IContext.context();
    }
}
