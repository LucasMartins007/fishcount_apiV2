package com.fishcount.api.validators.pattern;

import com.fishcount.api.infrastructure.context.IContext;
import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.pattern.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;

/**
 *
 * @author lucas
 * @param <T>
 */
public abstract class AbstractValidatorImpl<T extends AbstractEntity<?>> implements IValidator<T> {

    private Class<T> entityClass;

    protected AbstractValidatorImpl() {
        resolverClass(entityClass);
    }

    private IContext getContext() {
        return IContext.context();
    }

    public JpaRepository<T, Number> getRepository() {
        return getContext().getRepositoryFromClass(entityClass);
    }

    public <R extends JpaRepository<T, ?>> R getRepository(Class<R> classRespository) {
        return getContext().getBean(classRespository);
    }

    public <R extends IAbstractService<T, ?, ?>> R getService(Class<R> classService) {
        return getContext().getBean(classService);
    }

    public T findAndValidate(Number id) {
        if (id == null) {
            throw new FcRuntimeException(EnumFcInfraException.NULL_POINTER_EXCEPTION);
        }

        return getRepository()
                .findById(id)
                .orElseThrow(() -> new FcRuntimeException(EnumFcInfraException.ENTITY_NOT_FOUND, getEntityName(), id));
    }

    public String getEntityName() {
        return entityClass.getSimpleName();
    }

    private Class<T> resolverClass(Class<T> clazzType) {
        if (entityClass == null) {
            if (clazzType == null) {
                entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            } else {
                entityClass = clazzType;
            }
        }
        return entityClass;
    }
}
