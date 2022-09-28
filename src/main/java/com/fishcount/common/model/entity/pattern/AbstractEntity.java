package com.fishcount.common.model.entity.pattern;

import com.fishcount.common.model.pattern.IIdentifier;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;
import java.util.Date;

/**
 * @param <T>
 * @author lucas
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<T extends Number> implements IIdentifier<T> {

    @Column(name = "ativo")
    protected boolean ativo;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataAtualizacao;

    @PrePersist
    private void prePersist() {
        this.dataInclusao = DateUtil.getDate();
        this.dataAtualizacao = DateUtil.getDate();
        this.ativo = true;
    }

    @PreUpdate
    private void preUpdate() {
        this.dataAtualizacao = DateUtil.getDate();
        this.ativo = true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + getId() + ")";
    }

    @Override
    @SuppressWarnings("unchecked")
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
