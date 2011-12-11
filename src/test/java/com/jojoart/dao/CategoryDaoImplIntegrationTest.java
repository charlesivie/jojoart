package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:52
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/spring-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class CategoryDaoImplIntegrationTest {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ImageDao imageDao;

    @Transactional
    @Test
    public void testInsert() {
        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        categoryDao.create(category);
        assertTrue(category.getId() > 0);
    }

    @Transactional
    @Test
    public void testUpdate() {
        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);

        categoryDao.create(category);
        Image image = imageDao.create(new Image("cow", "pic of a cow", "image/jpg", true, category));

        long createdId = category.getId();

        category.setName("updated");
        category.setDescription("updated");
        category.setActive(false);
        category.setDefaultCategory(false);
        category.setImage(image);

        categoryDao.update(category);

        Category actual = categoryDao.read(Category.class, createdId);
        Category expected = new Category("updated", "updated", false, false, image);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    public void testDelete() {
        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        Category category2 = new Category("Landscapes", "outdoors landscapes", true, true, null);

        categoryDao.create(category);
        categoryDao.create(category2);

        long category2Id = category2.getId();

        categoryDao.delete(category2);

        Category nullCategory = categoryDao.read(Category.class, category2Id);

        assertNull(nullCategory);
    }

    @Transactional
    @Test
    public void testList() {

        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        Category category2 = new Category("Landscapes", "outdoors landscapes", true, true, null);

        categoryDao.create(category);
        categoryDao.create(category2);

        List<Category> categories = categoryDao.list(Category.class);

        assertTrue(categories.size()==2);

    }

    @Transactional
    @Test
    public void testListWithOffsetAndLimit() {

        for(int i = 0;i<20;i++){
            categoryDao.create(new Category("Landscapes", "outdoors landscapes", true, true, null));
        }

        List<Category> categories = categoryDao.list(Category.class, 10, 10);

        assertTrue(categories.size()==10);

    }

    @Transactional
    @Test
    public void testFind(){

        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);

        categoryDao.create(category);

        long createdId = category.getId();

        Category actual = categoryDao.read(Category.class, createdId);
        Category expected = new Category("Landscapes", "outdoors landscapes", true, true, null);

        assertEquals(expected, actual);
    }


}
