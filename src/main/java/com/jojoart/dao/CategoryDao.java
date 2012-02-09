package com.jojoart.dao;

import com.jojoart.domain.Category;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:41
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryDao extends GenericDao<Category, Long> {
    List<Category> getActiveCategoriesOrderByIsDefaultCategory();
    Category findDefaultCategory();
    Category findCategoryByName(String name);
}
