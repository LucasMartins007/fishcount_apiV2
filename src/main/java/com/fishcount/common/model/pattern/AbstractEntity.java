package com.fishcount.common.model.pattern;

import javax.persistence.MappedSuperclass;

import org.hibernate.proxy.HibernateProxyHelper;

/**
 *
 * @author lucas
 * @param <T>
 */
@MappedSuperclass
public abstract class AbstractEntity<T extends Number> implements IIdentifier<T> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + getId() + ")";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        final Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
        if (getClass() != objClass) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        final AbstractEntity<T> other = (AbstractEntity<T>) obj;
        if (this.getId() != null && other.getId() == null) {
            return false;
        }

        if (other.getId() != null && this.getId() == null) {
            return false;
        }

        if (this.getId() == null && other.getId() == null) {
            return false;
        }

        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

}
