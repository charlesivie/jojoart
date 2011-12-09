package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class ImageDaoImplIntegrationTest {
   
    @Autowired
    ImageDao imageDao;
    @Autowired
    ImageGroupDao imageGroupDao;
    @Autowired
    CategoryDao categoryDao;
    ImageGroup imageGroup;
    ImageGroup imageGroup2;
    Category category;

    @Before
    public void setup(){
        category = categoryDao.create(new Category("Landscapes", "outdoors landscapes", true, true));
        imageGroup = imageGroupDao.create(new ImageGroup("image imageGroup", "image imageGroup description", true, false, category));
        imageGroup2 = imageGroupDao.create(new ImageGroup("image imageGroup2", "image imageGroup description2", true, false, category));
    }

    @Transactional
    @Test
    public void getAllImagesForGroupShouldGetThreeImages(){

        List<Image> expectedImages = new ArrayList<Image>();
        for(int i=0;i<3;i++){
            Image image = new Image(1, "mime", "bytes".getBytes(), "name", "description", true, true, imageGroup);
            imageDao.create(image);
            expectedImages.add(image);
        }
        for(int i=0;i<3;i++){
            Image image = new Image(2, "mime", "bytes".getBytes(), "name", "description", true, true, imageGroup2);
            imageDao.create(image);
        }

        List<Image> actualImages = imageDao.getAllImagesForGroup(imageGroup);

        assertEquals(expectedImages, actualImages);
    }

    @Transactional
    @Test
    public void testInsert() {
        Image image = new Image(1, "mimetype", "something".getBytes(), "name", "description", true, true, imageGroup);
        imageDao.create(image);
        assertTrue(image.getId() > 0);
    }

    @Transactional
    @Test
    public void testUpdate() {

        Image image = new Image(1, "mimetype", "something".getBytes(), "name", "description", true, true, imageGroup);

        imageDao.create(image);

        long createdId = image.getId();

        image.setName("updated");
        image.setImageSizeType(2);
        image.setDescription("updated");
        image.setMimeType("updated");
        image.setImageBlob("updated".getBytes());
        image.setActive(false);
        image.setDefaultImage(false);

        imageDao.update(image);

        Image actual = imageDao.read(Image.class, createdId);
        Image expected = new Image(2, "updated", "updated".getBytes(), "updated", "updated", false, false, imageGroup);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    public void testDelete() {
        Image image = new Image(1, "mimetype", "something".getBytes(), "name", "description", true, true, imageGroup);
        Image image2 = new Image(1, "mimetype", "something".getBytes(), "name", "description", true, true, imageGroup);

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

        Image image = new Image(1, "mimetype", "something".getBytes(), "name", "description", true, true, imageGroup);
        Image image2 = new Image(1, "mimetype", "something".getBytes(), "name", "description", true, true, imageGroup);

        imageDao.create(image);
        imageDao.create(image2);

        List<Image> images = imageDao.list(Image.class);

        assertTrue(images.size() == 2);

    }

    @Transactional
    @Test
    public void testListWithOffsetAndLimit() {

        for (int i = 0; i < 20; i++) {
            imageDao.create(new Image(1, "a", "a".getBytes(), "a", "a", true, true, imageGroup));
        }

        List<Image> images = imageDao.list(Image.class, 10, 10);

        assertTrue(images.size() == 10);

    }

    @Transactional
    @Test
    public void testFind() {

        Image image = new Image(1, "a", "a".getBytes(), "a", "a", true, true, imageGroup);

        imageDao.create(image);

        long createdId = image.getId();

        Image actual = imageDao.read(Image.class, createdId);
        Image expected = new Image(1, "a", "a".getBytes(), "a", "a", true, true, imageGroup);

        assertEquals(expected, actual);
    }

}
