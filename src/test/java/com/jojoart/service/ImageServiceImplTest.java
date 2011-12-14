package com.jojoart.service;

import com.jojoart.dao.ImageDao;
import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {
    
    private ImageServiceImpl imageService;
    @Mock ImageDao mockImageDao;
    @Mock ImageVersionDao mockImageVersionDao;
    @Mock Category mockCategory;
    @Mock MultipartFile mockMultipartFile;
    
    @Before
    public void setup(){
        imageService = new ImageServiceImpl();
        imageService.setImageDao(mockImageDao);
        imageService.setImageVersionDao(mockImageVersionDao);
    }

    @Test
    public void resize_and_store_should_store_all_image_versions() throws IOException {

        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);
        
        verify(mockImageVersionDao, times(ImageType.values().length)).create(Matchers.<ImageVersion>anyObject());
    }

    @Test
    public void resize_and_store_should_remove_all_old_image_versions() {
        fail("not yet implemented");
    }

    @Test
    public void resize_and_store_should_resize_all_images() {
        fail("not yet implemented");
    }

    @Test
    public void resize_and_store_stores_new_image() throws IOException {
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageDao).create(expected);
    }

    @Test
    public void resize_and_store_updates_existing_image() throws IOException {
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);
        expected.setId(1l);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageDao).update(expected);
    }
    
}
