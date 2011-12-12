package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageVersion;
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
public class ImageVersionDaoImplIntegrationTest2 {
   
    @Autowired
    ImageVersionDao imageVersionDao;
    @Autowired
    ImageDao imageDao;
    @Autowired
    CategoryDao categoryDao;
    Image image;
    Image image2;
    Category category;

    @Before
    public void setup(){
        category = categoryDao.create(new Category("Landscapes", "outdoors landscapes", true, true));
        image = imageDao.create(new Image("cow", "picture of a cow", "image/jpeg", true, category));
        image2 = imageDao.create(new Image("cow", "picture of a cow", "image/jpeg", true, category));
    }

    @Transactional
    @Test
    public void getAllImageVersionsShouldGetThreeImages(){

        List<ImageVersion> expectedImages = new ArrayList<ImageVersion>();
        for(int i=0;i<3;i++){
            ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);
            imageVersionDao.create(imageVersion);
            expectedImages.add(imageVersion);
        }
        for(int i=0;i<3;i++){
            ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image2);
            imageVersionDao.create(imageVersion);
        }

        List<ImageVersion> actualImages = imageVersionDao.getAllImageVersions(image);

        assertEquals(expectedImages, actualImages);
    }

    @Transactional
    @Test
    public void testInsert() {
        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);
        imageVersionDao.create(imageVersion);
        assertTrue(imageVersion.getId() > 0);
    }

    @Transactional
    @Test
    public void testUpdate() {

        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);

        imageVersionDao.create(imageVersion);

        long createdId = imageVersion.getId();

        imageVersion.setImage(image2);
        imageVersion.setWidth(ImageType.THUMBNAIL.getWidth());
        imageVersion.setImageBlob("updated".getBytes());

        imageVersionDao.update(imageVersion);

        ImageVersion actual = imageVersionDao.read(ImageVersion.class, createdId);
        ImageVersion expected = new ImageVersion("updated".getBytes(), ImageType.THUMBNAIL.getWidth(), image2);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    public void testDelete() {
        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);
        ImageVersion imageVersion2 = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);

        imageVersionDao.create(imageVersion);
        imageVersionDao.create(imageVersion2);

        long group2Id = imageVersion2.getId();

        imageVersionDao.delete(imageVersion2);

        ImageVersion nullImageVersion = imageVersionDao.read(ImageVersion.class, group2Id);

        assertNull(nullImageVersion);
    }

    @Transactional
    @Test
    public void testList() {

        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);
        ImageVersion imageVersion2 = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);

        imageVersionDao.create(imageVersion);
        imageVersionDao.create(imageVersion2);

        List<ImageVersion> imageVersions = imageVersionDao.list(ImageVersion.class);

        assertTrue(imageVersions.size() == 2);

    }

    @Transactional
    @Test
    public void testListWithOffsetAndLimit() {

        for (int i = 0; i < 20; i++) {
            imageVersionDao.create(new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image));
        }

        List<ImageVersion> imageVersions = imageVersionDao.list(ImageVersion.class, 10, 10);

        assertTrue(imageVersions.size() == 10);

    }

    @Transactional
    @Test
    public void testFind() {

        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);

        imageVersionDao.create(imageVersion);

        long createdId = imageVersion.getId();

        ImageVersion actual = imageVersionDao.read(ImageVersion.class, createdId);
        ImageVersion expected = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getWidth(), image);

        assertEquals(expected, actual);
    }

}
