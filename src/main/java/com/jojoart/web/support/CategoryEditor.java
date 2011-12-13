package com.jojoart.web.support;

import com.jojoart.dao.CategoryDao;
import com.jojoart.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 13/12/2011
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

public class CategoryEditor extends PropertyEditorSupport {

    @Autowired
    CategoryDao categoryDao;
    
    public void setAsText(String id) {
        setValue(categoryDao.read(Category.class, Long.valueOf(id)));
    }
    
}