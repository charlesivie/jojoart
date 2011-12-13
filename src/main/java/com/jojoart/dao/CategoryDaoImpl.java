package com.jojoart.dao;

import com.jojoart.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:43
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {
}
