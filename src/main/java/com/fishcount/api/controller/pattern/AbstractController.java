package com.fishcount.api.controller.pattern;

import com.fishcount.api.config.context.IContext;
import com.fishcount.api.converter.Converter;
import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.LoggerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lucas
 * @param <E>
 */
public abstract class AbstractController<E extends IAbstractService<?, ?, ?>> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ModelMapper mapper;
    
    private final Class<E> serviceClass;

    protected AbstractController() {
        this.serviceClass = resolverServiceClass(this.getClass());
    }

    protected E getService() {
        return getContext().getBean(serviceClass);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> C converterDTOParaEntity(D dto, Class<C> clazzEntity) {
        return mapper.map(dto, clazzEntity);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> List<C> converterDTOParaEntity(List<D> dtos, Class<C> clazzEntity) {
        return ListUtil.stream(dtos)
                .map(d -> mapper.map(d, clazzEntity))
                .collect(Collectors.toList());
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> D converterEntityParaDTO(C entity, Class<D> clazzDto) {
        return mapper.map(entity, clazzDto);
    }

    protected <D extends AbstractDTO<?>, C extends AbstractEntity<?>> List<D> converterEntityParaDTO(List<C> entitys, Class<D> clazzDto) {
        return ListUtil.stream(entitys)
                .map(c -> mapper.map(c, clazzDto))
                .collect(Collectors.toList());
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
