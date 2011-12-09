package com.jojoart.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:33
 * To change this template use File | Settings | File Templates.
 */

public interface GenericDao<T, PK extends Serializable> {
    T create(T t);
    T read(Class<T> clazz, PK id);
    T update(T t);
    void delete(T t);
    void flush();
    List<T> list(Class<T> clazz, int offset, int limit);
    List<T> list(Class<T> clazz);
}
