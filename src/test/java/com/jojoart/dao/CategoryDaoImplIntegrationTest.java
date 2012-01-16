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

import java.util.ArrayList;
import java.util.LinkedList;
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
@ContextConfiguration({"/META-INF/spring/applicationContext.xml"})
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

        assertTrue(categories.size() == 2);

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

    @Transactional
    @Test
    public void getActiveCategoriesOrderByIsDefaultCategory_should_only_return_active_categories(){

        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        Category category1 = new Category("Portraits", "portraits", true, false, null);
        Category category2 = new Category("Life", "life", false, false, null);
        Category category3 = new Category("Abstract", "abstract", false, false, null);

        categoryDao.create(category);
        categoryDao.create(category1);
        categoryDao.create(category2);
        categoryDao.create(category3);

        List<Category> actual = categoryDao.getActiveCategoriesOrderByIsDefaultCategory();

        for(Category actualCategory: actual){
            assertTrue(actualCategory.isActive());
        }
        assertEquals(2, actual.size());

    }

    @Transactional
    @Test
    public void getActiveCategoriesOrderByIsDefaultCategory_should_order_by_default(){

        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        Category category1 = new Category("Portraits", "portraits", true, true, null);
        Category category2 = new Category("Life", "life", true, false, null);
        Category category3 = new Category("Abstract", "abstract", true, false, null);

        categoryDao.create(category);
        categoryDao.create(category1);
        categoryDao.create(category2);
        categoryDao.create(category3);

        List<Category> actual = categoryDao.getActiveCategoriesOrderByIsDefaultCategory();
        
        List<Category> expected = new LinkedList<Category>();
        expected.add(category);
        expected.add(category1);
        expected.add(category2);
        expected.add(category3);

        List<Category> notExpected = new LinkedList<Category>();
        notExpected.add(category2);
        notExpected.add(category3);
        notExpected.add(category);
        notExpected.add(category1);

        assertEquals(expected, actual);
        assertNotSame(notExpected, actual);

    }

    @Test
    @Transactional
    public void findDefaultCategory_should_return_one_default_category(){

        Category category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        Category category1 = new Category("Portraits", "portraits", true, true, null);
        Category category2 = new Category("Life", "life", true, false, null);
        Category category3 = new Category("Abstract", "abstract", true, false, null);

        categoryDao.create(category);
        categoryDao.create(category1);
        categoryDao.create(category2);
        categoryDao.create(category3);

        Category actual = categoryDao.findDefaultCategory();

        assertEquals(category, actual);
    }


}
