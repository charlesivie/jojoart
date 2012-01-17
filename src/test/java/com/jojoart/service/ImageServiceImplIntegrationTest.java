package com.jojoart.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 17/01/2012
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ImageServiceImplIntegrationTest {

    @Test
    @Ignore("to be implemented")
    public void resizeAndStoreImage_should_store_imageVersions(){

    }

    @Test
    @Ignore("to be implemented")
    public void resizeAndStoreImage_should_store_image(){

    }

    @Test
    @Ignore("to be implemented")
    public void resizeAndStoreImage_should_find_mimeType_from_jpg(){

    }

    @Test
    @Ignore("to be implemented")
    public void resizeAndStoreImage_should_find_mimeType_from_gif(){

    }

    @Test
    @Ignore("to be implemented")
    public void resizeAndStoreImage_should_find_mimeType_from_png(){

    }

    @Test(expected = FileNotFoundException.class)
    @Ignore("to be implemented")
    public void resizeAndStoreImage_should_throw_fileNotFound(){

    }
}
