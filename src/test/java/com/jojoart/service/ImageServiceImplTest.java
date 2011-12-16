package com.jojoart.service;

import com.jojoart.dao.ImageDao;
import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import org.imgscalr.Scalr;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.openInputStream;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ImageIO.class, Scalr.class})
public class ImageServiceImplTest {

    private ImageServiceImpl imageService;
    @Mock
    ImageDao mockImageDao;
    @Mock
    ImageVersionDao mockImageVersionDao;
    @Mock
    Category mockCategory;
    @Mock
    MultipartFile mockMultipartFile;
    @Mock
    InputStream mockInputStream;
    @Mock
    BufferedImage mockBufferedImage;
    File testPng, testJpg, testGif;

    @Before
    public void setup() throws IOException {

        testPng = new ClassPathResource("image/sf.png").getFile();
        testJpg = new ClassPathResource("image/sf.jpg").getFile();
        testGif = new ClassPathResource("image/sf.gif").getFile();


        imageService = new ImageServiceImpl();
        imageService.setImageDao(mockImageDao);
        imageService.setImageVersionDao(mockImageVersionDao);
    }

    @Test
    public void resize_and_store_should_store_all_image_versions() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        when(mockMultipartFile.getInputStream()).thenReturn(mockInputStream);
        when(ImageIO.read(mockMultipartFile.getInputStream())).thenReturn(mockBufferedImage);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageVersionDao, times(ImageType.values().length)).create(Matchers.<ImageVersion>anyObject());
    }

    @Test
    public void resize_and_store_should_remove_all_old_image_versions() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image image = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.getMaxSize(), image);
        ImageVersion imageVersion2 = new ImageVersion("bytes".getBytes(), ImageType.THUMBNAIL.getMaxSize(), image);

        List<ImageVersion> imageVersions = new ArrayList<ImageVersion>();
        imageVersions.add(imageVersion);
        imageVersions.add(imageVersion2);

        when(mockMultipartFile.getInputStream()).thenReturn(mockInputStream);
        when(mockImageVersionDao.getAllImageVersions(image)).thenReturn(imageVersions);
        when(ImageIO.read(mockMultipartFile.getInputStream())).thenReturn(mockBufferedImage);

        imageService.resizeAndStoreImage(image, mockMultipartFile);

        verify(mockImageVersionDao, times(ImageType.values().length)).delete(Matchers.<ImageVersion>anyObject());

    }

    @Test
    public void resize_and_store_stores_new_image() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageDao).create(expected);
    }

    @Test
    public void resize_and_store_updates_existing_image() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);
        expected.setId(1l);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageDao).update(expected);
    }

}
