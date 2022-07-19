package com.fishcount.api.repository.pattern;

import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author Lucas Martins
 */
@NoRepositoryBean
public interface IRepositoryOperations<T> {

    Class<T> getEntityClass();

    void setClass(Class<T> clazz);

    void flush();

    void clear();

    void refresh(T entity);

    T refresh(Integer id);

    T getReference(Integer primaryKey);

    void detach(Object obj);

    void persist(T entity);

    T merge(T entity);

    T mergeOrPersist(T entity);

    T find(Number id);

    T find(T entity);

    T findAndValidate(Integer id);

    T findLock(Integer id);

    T findLockNoWait(Integer id);

    T findRefresh(Integer id);

    void remove(T entity);

    void lock(T entity);

    void lockNoWait(T entity);

}

