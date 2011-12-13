package com.jojoart.web.support;

import com.jojoart.dao.CategoryDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.CategoryEditor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 13/12/2011
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/spring-config.xml", "/spring/spring-mvc-servlet.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class CategoryEditorIntegrationTest {
    
    @Autowired
    CategoryEditor categoryEditor;
    @Autowired
    CategoryDao categoryDao;
    
    @Test
    public void setAsTextShouldGetCategory() throws Exception {
        Category category = new Category("category name", "category description", true, true);
        categoryDao.create(category);
        categoryEditor.setAsText(String.valueOf(category.getId()));
    }
}
