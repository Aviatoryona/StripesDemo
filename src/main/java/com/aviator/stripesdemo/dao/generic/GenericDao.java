/*
 *
 *  * Copyright (c) 2021.  Aviator
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package com.aviator.stripesdemo.dao.generic;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@ManagedBean
public class GenericDao<T, V extends Serializable> implements GenericDaoI<T, V> {

    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public GenericDao() {
        this.entityClass =
                (Class<T>)
                        ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public GenericDao(final Class<T> persistentClass) {
        super();
        this.entityClass = persistentClass;
    }

    @Override
    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public T edit(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    @Override
    public boolean delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        return true;
    }

    @Override
    public boolean remove(V id) {
        return delete(find(id));
    }

    @Override
    public T find(V id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public long count() {
        CriteriaQuery<Long> cq
                = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return q.getSingleResult();
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
