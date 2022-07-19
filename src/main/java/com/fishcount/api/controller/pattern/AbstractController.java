package com.fishcount.api.controller.pattern;

import com.fishcount.api.config.context.IContext;
import com.fishcount.api.converter.Converter;
import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author lucas
 * @param <E>
 */
public abstract class AbstractController<E extends IAbstractService<?, ?, ?>> {

    @Autowired
    private HttpServletRequest request;
    
    private final Class<E> serviceClass;

    protected AbstractController() {
        this.serviceClass = resolverServiceClass(this.getClass());
    }

    protected E getService() {
        return getContext().getBean(serviceClass);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> C converterDTOParaEntity(D dto, Class<C> clazzEntity) {
        return Converter.converterDTOParaEntity(dto, clazzEntity);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> List<C> converterDTOParaEntity(List<D> dtos, Class<C> clazzEntity) {
        return Converter.converterDTOParaEntity(dtos, clazzEntity);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> D converterEntityParaDTO(C entity, Class<D> clazzDto) {
        return Converter.converterEntityParaDTO(entity, clazzDto);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> List<D> converterEntityParaDTO(List<C> entitys, Class<D> clazzDto) {
        return Converter.converterEntityParaDTO(entitys, clazzDto);
    }

    @SuppressWarnings("unchecked")
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
