package com.jojoart.service;

import com.jojoart.dao.ImageDao;
import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.ImageType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.apache.commons.io.FileUtils.openInputStream;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplResizeTest {

    private ImageServiceImpl imageService;
    @Mock ImageDao mockImageDao;
    @Mock ImageVersionDao mockImageVersionDao;
    @Mock MultipartFile mockMultipartFile;
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
    public void getResizedBytes_should_resize_png() throws IOException {

        when(mockMultipartFile.getInputStream()).thenReturn(openInputStream(testPng));
        when(mockMultipartFile.getOriginalFilename()).thenReturn(testPng.getName());

        BufferedImage bufferedImage = ImageIO.read(mockMultipartFile.getInputStream());

        byte[] bytes = imageService.getResizedBytes(bufferedImage, mockMultipartFile, ImageType.THUMBNAIL.getMaxSize());

        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage resultImage = ImageIO.read(inputStream);

        assertTrue(resultImage.getHeight() <= ImageType.THUMBNAIL.getMaxSize()
                && resultImage.getWidth() <= ImageType.THUMBNAIL.getMaxSize());
    }

    @Test
    public void getResizedBytes_should_resize_jpg() throws IOException {

        when(mockMultipartFile.getInputStream()).thenReturn(openInputStream(testJpg));
        when(mockMultipartFile.getOriginalFilename()).thenReturn(testJpg.getName());
        BufferedImage bufferedImage = ImageIO.read(mockMultipartFile.getInputStream());

        byte[] bytes = imageService.getResizedBytes(bufferedImage, mockMultipartFile, ImageType.THUMBNAIL.getMaxSize());

        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage resultImage = ImageIO.read(inputStream);

        assertTrue(resultImage.getHeight() <= ImageType.THUMBNAIL.getMaxSize()
                && resultImage.getWidth() <= ImageType.THUMBNAIL.getMaxSize());
    }

    @Test
    public void getResizedBytes_should_resize_gif() throws IOException {

        when(mockMultipartFile.getInputStream()).thenReturn(openInputStream(testGif));
        when(mockMultipartFile.getOriginalFilename()).thenReturn(testGif.getName());
        BufferedImage bufferedImage = ImageIO.read(mockMultipartFile.getInputStream());

        byte[] bytes = imageService.getResizedBytes(bufferedImage, mockMultipartFile, ImageType.NORMAL.getMaxSize());

        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage resultImage = ImageIO.read(inputStream);

        assertTrue(resultImage.getHeight() <= ImageType.NORMAL.getMaxSize()
                && resultImage.getWidth() <= ImageType.NORMAL.getMaxSize());
    }

    @Test
    public void getResizedBytes_should_not_resize_up() throws IOException {

        when(mockMultipartFile.getInputStream()).thenReturn(openInputStream(testGif));
        when(mockMultipartFile.getOriginalFilename()).thenReturn(testGif.getName());

        BufferedImage originalImage = ImageIO.read(mockMultipartFile.getInputStream());

        byte[] bytes = imageService.getResizedBytes(originalImage, mockMultipartFile, originalImage.getWidth()+100);

        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage resultImage = ImageIO.read(inputStream);

        assertTrue(originalImage.getHeight() == resultImage.getHeight()
                && originalImage.getWidth() == resultImage.getWidth());

    }

}
