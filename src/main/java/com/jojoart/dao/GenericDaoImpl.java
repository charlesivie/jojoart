package com.jojoart.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    @Transactional
    public T read(Class<T> clazz, PK id) {
        return this.entityManager.find(clazz, id);
    }

    @Transactional
    public T update(T t) {
        this.entityManager.merge(t);
        return t;
    }

    @Transactional
    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }

    public void flush() {
        this.entityManager.flush();
    }

    @Transactional
    public List<T> list(Class<T> clazz, int offset, int limit) {
        StringBuilder qry = new StringBuilder();
        qry.append("from ");
        qry.append(clazz.getSimpleName());
        return this.entityManager.createQuery(qry.toString(), clazz)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    @Transactional
    public List<T> list(Class<T> clazz) {
        StringBuilder qry = new StringBuilder();
        qry.append("from ");
        qry.append(clazz.getSimpleName());
        return this.entityManager.createQuery(qry.toString(), clazz).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}