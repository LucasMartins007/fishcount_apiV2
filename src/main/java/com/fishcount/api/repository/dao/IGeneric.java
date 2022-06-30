package com.fishcount.api.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author Lucas Martins
 */
@NoRepositoryBean
public interface IGeneric<T, I> {

    JpaRepository<T, I> getRepository();

    JpaSpecificationExecutor<T> getSpecRepository();

    <S extends JpaSpecificationExecutor<T>> S getSpecRepository(Class<S> specClass);

}
