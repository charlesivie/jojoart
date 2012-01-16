package com.jojoart.dao;

import com.jojoart.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:43
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {
    public List<Category> getActiveCategoriesOrderByIsDefaultCategory() {

        return getEntityManager()
                .createQuery("SELECT a FROM Category a " +
                        "WHERE :active = a.active " +
                        "ORDER BY a.isDefaultCategory DESC", Category.class)
                .setParameter("active", true)
                .getResultList();
    }

    public Category findDefaultCategory() {
        return (Category) getEntityManager()
                .createQuery("SELECT a FROM Category a WHERE a.isDefaultCategory = true")
                .setMaxResults(1)
                .getSingleResult();
    }
}
