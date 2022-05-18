/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fishcount.api.infrastructure.context;

import com.fishcount.common.model.pattern.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

/**
 *
 * @author lucas
 */
public interface IContext {
    
    IContext context = new ContextImpl();

    void createBean(Class domainClass);

    void destroyBean(Class domainClass);

    <T> T getBean(Class<T> domainClass);

    <T> Collection<T> getBeansInterface(Class<T> domainClassInterface);

    boolean hasRepositoryFor(Class<?> domainClass);

    <T extends AbstractEntity<?>, ID extends Object> JpaRepository<T, ID> getRepositoryFromClass(Class<T> domainClass);

    <T extends AbstractEntity<?>> JpaSpecificationExecutor<T> getSpecRepositoryFromClass(Class<T> domainClass);

    static IContext context() {
        return context;
    }
    
}
