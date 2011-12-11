package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/spring-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ImageVersionDaoImplIntegrationTest {

    @Autowired
    ImageDao imageDao;
    @Autowired
    CategoryDao categoryDao;
    Category category;
    Category category2;

    @Before
    public void setup(){
        category = categoryDao.create(new Category("Landscapes", "outdoors landscapes", true, true));
        category2 = categoryDao.create(new Category("Landscapes 2", "outdoors landscapes 2", true, true));
    }

    @Transactional
    @Test
    public void testInsert() {
        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, category);
        imageDao.create(image);
        assertTrue(image.getId() > 0);
    }

    @Transactional
    @Test
    public void testUpdate() {
        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, category);

        imageDao.create(image);

        long createdId = image.getId();

        image.setName("updated");
        image.setDescription("updated");
        image.setActive(false);
        image.setMimeType("image/gif");
        image.setCategory(category2);

        imageDao.update(image);

        Image actual = imageDao.read(Image.class, createdId);
        Image expected = new Image("updated", "updated", "image/gif", false, category2);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    public void testDelete() {
        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, category);
        Image image2 = new Image("cow", "picture of a cow", "image/jpeg", true, category);

        imageDao.create(image);
        imageDao.create(image2);

        long group2Id = image2.getId();

        imageDao.delete(image2);

        Image nullImage = imageDao.read(Image.class, group2Id);

        assertNull(nullImage);
    }

    @Transactional
    @Test
    public void testList() {

        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, category);
        Image image2 = new Image("cow", "picture of a cow", "image/jpeg", true, category);

        imageDao.create(image);
        imageDao.create(image2);

        List<Image> imageList = imageDao.list(Image.class);

        assertTrue(imageList.size() == 2);

    }

    @Transactional
    @Test
    public void testListWithOffsetAndLimit() {

        for (int i = 0; i < 20; i++) {
            imageDao.create(new Image("cow", "picture of a cow", "image/jpeg", true, category));
        }

        List<Image> imageList = imageDao.list(Image.class, 10, 10);

        assertTrue(imageList.size() == 10);

    }

    @Transactional
    @Test
    public void testFind() {

        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, category);

        imageDao.create(image);

        long createdId = image.getId();

        Image actual = imageDao.read(Image.class, createdId);
        Image expected = new Image("cow", "picture of a cow", "image/jpeg", true, category);

        assertEquals(expected, actual);
    }

}
