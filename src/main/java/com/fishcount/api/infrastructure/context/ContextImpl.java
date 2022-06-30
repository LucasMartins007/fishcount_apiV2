package com.fishcount.api.infrastructure.context;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.pattern.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.support.Repositories;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author lucas
 */
@Configuration
public class ContextImpl implements ApplicationContextAware, IContext {

    private ApplicationContext applicationContext;

    private Repositories repositories;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.repositories = new Repositories(applicationContext);
    }

    @Override
    public void createBean(Class<?> domainClass) {
        try {
            final ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
            final DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

            if (!beanFactory.containsBean(domainClass.getSimpleName())) {
                final GenericBeanDefinition generatorBean = new GenericBeanDefinition();
                generatorBean.setBeanClass(domainClass);
                generatorBean.setLazyInit(true);

                defaultListableBeanFactory.registerBeanDefinition(domainClass.getSimpleName(), generatorBean);
            }
        } catch (IllegalStateException | BeanDefinitionStoreException e) {
            throw new FcRuntimeException(e.getMessage());
        }
    }

    @Override
    public void destroyBean(Class<?> domainClass) {
        try {
            final ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
            final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;

            if (beanFactory.containsBean(domainClass.getSimpleName())) {
                beanDefinitionRegistry.removeBeanDefinition(domainClass.getSimpleName());
            }
        } catch (IllegalStateException | NoSuchBeanDefinitionException e) {
            throw new FcRuntimeException(e.getMessage());
        }
    }

    @Override
    public <T> T getBean(Class<T> domainClass) {
        try {
            return applicationContext.getAutowireCapableBeanFactory().getBean(domainClass);
        } catch (IllegalStateException | BeansException e) {
            throw new FcRuntimeException(e.getMessage());
        }
    }

    @Override
    public <T> Collection<T> getBeansInterface(Class<T> domainClassInterface) {
        try {
            if (domainClassInterface.isInterface()) {
                final Map<String, T> beansOfType = applicationContext.getBeansOfType(domainClassInterface);

                return beansOfType.values();
            }
            return Collections.emptyList();
        } catch (BeansException e) {
            throw new FcRuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean hasRepositoryFor(final Class<?> domainClass) {
        return repositories.hasRepositoryFor(domainClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity<?>, I> JpaRepository<T, I> getRepositoryFromClass(Class<T> domainClass) {
        return (JpaRepository<T, I>) repositories.getRepositoryFor(domainClass)
                .orElseThrow(() -> new FcRuntimeException(
                EnumFcInfraException.REPOSITORY_NOT_FOUND,
                JpaRepository.class.getSimpleName(),
                domainClass.getSimpleName()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity<?>> JpaSpecificationExecutor<T> getSpecRepositoryFromClass(Class<T> domainClass) {
        return (JpaSpecificationExecutor<T>) repositories.getRepositoryFor(domainClass)
                .orElseThrow(() -> new FcRuntimeException(
                EnumFcInfraException.REPOSITORY_NOT_FOUND,
                JpaRepository.class.getSimpleName(),
                domainClass.getSimpleName()));
    }

}
