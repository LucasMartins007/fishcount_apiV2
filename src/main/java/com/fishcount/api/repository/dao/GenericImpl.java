
package com.fishcount.api.repository.dao;

import com.fishcount.api.infrastructure.context.IContext;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.pattern.AbstractEntity;
import com.fishcount.common.utils.LoggerUtil;
import com.fishcount.common.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import org.springframework.data.repository.Repository;


/**
 *
 * @author Lucas Martins
 */
@Transactional(readOnly = true)
@org.springframework.stereotype.Repository
public class GenericImpl<T extends AbstractEntity<?>, ID> implements IOperations<T>, IGeneric<T, ID>, Repository<T, ID> {

    @Lazy
    @Autowired
    private IContext context;

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;
    private final Map<String, Object> mapLockNoWait = Collections.singletonMap("javax.persistence.lock.timeout", (Object) 0);

    public GenericImpl() {
        resolverClass(entityClass);
    }

    public <R extends Repository> R getRepository(Class<R> classRespository) {
        return getContext().getBean(classRespository);
    }

    private IContext getContext() {
        return IContext.context();
    }

    @Override
    public JpaRepository<T, ID> getRepository() {
        return resolverContext().getRepositoryFromClass(getEntityClass());
    }

    @Override
    public JpaSpecificationExecutor<T> getSpecRepository() {
        return resolverContext().getSpecRepositoryFromClass(entityClass);
    }

    @Override
    public <S extends JpaSpecificationExecutor> S getSpecRepository(Class<S> specClass) {
        return resolverContext().getBean(specClass);
    }

    private IContext resolverContext() {
        return ((context != null) ? context : IContext.context());
    }

    public Class<T> getEntityClass() {
        return resolverClass(this.entityClass);
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setClass(Class clazz) {
        this.entityClass = resolverClass(clazz);
    }

    @Transactional
    public void flush() {
        getEntityManager().flush();
    }

    @Transactional
    public void clear() {
        getEntityManager().clear();
    }

    @Transactional
    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public T refresh(Integer id) {
        T entity = getReference(id);
        refresh(entity);
        return entity;
    }

    public T getReference(Integer primaryKey) {
        return getEntityManager().getReference(entityClass, primaryKey);
    }

    @Transactional
    public void detach(Object obj) {
        getEntityManager().detach(obj);
    }

    @Transactional
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Transactional
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional
    public T mergeOrPersist(T entity) {
        T entityDB = find(entity);

        if (entityDB == null) {
            persist(entity);
        } else {
            entity = merge(entity);
        }

        return entity;
    }

    public T find(Number id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public T find(T entity) {
        Number id = entity.getId();
        return find(id);
    }

    public T findAndValidate(Integer id) {
        T entity = find(id);

        if (entity == null) {
            throw new FcRuntimeException(EnumFcInfraException.ENTITY_NOT_FOUND, getEntityName(), id);
        }
        return entity;
    }

    @Transactional
    public T findLock(Integer id) {
        return getEntityManager().find(entityClass, id, LockModeType.PESSIMISTIC_WRITE);
    }

    @Transactional
    public T findLockNoWait(Integer id) {
        return getEntityManager().find(entityClass, id, LockModeType.PESSIMISTIC_WRITE, mapLockNoWait);
    }

    public T findRefresh(Integer id) {
        T entity = find(id);
        if (entity != null) {
            refresh(entity);
        }
        return entity;
    }

    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    @Transactional
    public void lock(T entity) {
        getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE);
    }

    @Transactional
    public void lockNoWait(T entity) {
        getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE, mapLockNoWait);
    }

    protected Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    protected Query createQuery(StringBuilder query) {
        return createQuery(query.toString());
    }

    protected Query createQuery(CriteriaQuery<T> criteriaQuery) {
        return getEntityManager().createQuery(criteriaQuery);
    }

    protected Query createQuery(String sqlString, Class entityClass) {
        return getEntityManager().createQuery(sqlString, entityClass);
    }

    protected TypedQuery<T> createTypedQuery(String query) {
        return getEntityManager().createQuery(query, getEntityClass());
    }

    protected TypedQuery<T> createTypedQuery(StringBuilder query) {
        return createTypedQuery(query.toString());
    }

    protected <C> TypedQuery<C> createTypedQuery(String sqlString, Class<C> entityClass) {
        return getEntityManager().createQuery(sqlString, entityClass);
    }

    protected Query createNativeQuery(String query) {
        return getEntityManager().createNativeQuery(query, getEntityClass());
    }

    protected Query createNativeQuery(StringBuilder query) {
        return createNativeQuery(query.toString());
    }

    protected Query createNativeQuery(StringBuilder query, Class entityClass) {
        return getEntityManager().createNativeQuery(query.toString(), entityClass);
    }

    protected String getEntityName() {
        if (entityClass.isAnnotationPresent(Entity.class)) {
            Entity annotation = entityClass.getAnnotation(Entity.class);
            if (StringUtil.isNotNullOrEmpty(annotation.name())) {
                return annotation.name();
            }
        }

        return entityClass.getSimpleName();
    }

    protected Class<T> resolverClass(Class<T> clazzType) {
        try {
            if (entityClass == null) {
                if (clazzType == null) {
                    final Type genericSuperclass = getClass().getGenericSuperclass();

                    if (genericSuperclass instanceof ParameterizedType) {
                        entityClass = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
                    }
                } else {
                    entityClass = clazzType;
                }
            }
            return entityClass;
        } catch (Exception e) {
            LoggerUtil.getLogger(getClass()).error(e.getMessage());
            return null;
        }
    }
}

