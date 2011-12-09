package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.ImageGroup;
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
public class ImageGroupDaoImplIntegrationTest {

    @Autowired
    ImageGroupDao imageGroupDao;
    @Autowired
    CategoryDao categoryDao;
    Category category;

    @Before
    public void setup(){
        category = categoryDao.create(new Category("Landscapes", "outdoors landscapes", true, true));
    }

    @Transactional
    @Test
    public void testInsert() {
        ImageGroup imageGroup = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);
        imageGroupDao.create(imageGroup);
        assertTrue(imageGroup.getId() > 0);
    }

    @Transactional
    @Test
    public void testUpdate() {
        ImageGroup imageGroup = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);

        imageGroupDao.create(imageGroup);

        long createdId = imageGroup.getId();

        imageGroup.setName("updated");
        imageGroup.setDescription("updated");
        imageGroup.setActive(false);
        imageGroup.setDefaultGroup(false);

        imageGroupDao.update(imageGroup);

        ImageGroup actual = imageGroupDao.read(ImageGroup.class, createdId);
        ImageGroup expected = new ImageGroup("updated", "updated", false, false, category);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    public void testDelete() {
        ImageGroup imageGroup = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);
        ImageGroup imageGroup2 = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);

        imageGroupDao.create(imageGroup);
        imageGroupDao.create(imageGroup2);

        long group2Id = imageGroup2.getId();

        imageGroupDao.delete(imageGroup2);

        ImageGroup nullImageGroup = imageGroupDao.read(ImageGroup.class, group2Id);

        assertNull(nullImageGroup);
    }

    @Transactional
    @Test
    public void testList() {

        ImageGroup imageGroup = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);
        ImageGroup imageGroup2 = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);

        imageGroupDao.create(imageGroup);
        imageGroupDao.create(imageGroup2);

        List<ImageGroup> imageGroups = imageGroupDao.list(ImageGroup.class);

        assertTrue(imageGroups.size() == 2);

    }

    @Transactional
    @Test
    public void testListWithOffsetAndLimit() {

        for (int i = 0; i < 20; i++) {
            imageGroupDao.create(new ImageGroup("image group", "image group description", true, false, category));
        }

        List<ImageGroup> imageGroups = imageGroupDao.list(ImageGroup.class, 10, 10);

        assertTrue(imageGroups.size() == 10);

    }

    @Transactional
    @Test
    public void testFind() {

        ImageGroup imageGroup = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);;

        imageGroupDao.create(imageGroup);

        long createdId = imageGroup.getId();

        ImageGroup actual = imageGroupDao.read(ImageGroup.class, createdId);
        ImageGroup expected = new ImageGroup("image imageGroup", "image imageGroup description", true, false, category);

        assertEquals(expected, actual);
    }

}
