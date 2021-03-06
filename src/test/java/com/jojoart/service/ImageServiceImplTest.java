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

import static org.junit.Assert.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    @Mock private ImageDao mockImageDao;
    @Mock private ImageVersionDao mockImageVersionDao;
    @Mock private ImageVersion mockImageVersion;
    @Mock private Category mockCategory;
    @Mock private MultipartFile mockMultipartFile;
    @Mock private InputStream mockInputStream;
    @Mock private BufferedImage mockBufferedImage;
    @Mock private Image mockImage;
    @Mock private AmazonS3Service mockAmazonS3Service;
    private File testPng, testJpg, testGif;

    @Before
    public void setup() throws IOException {
        testPng = new ClassPathResource("image/sf.png").getFile();
        testJpg = new ClassPathResource("image/sf.jpg").getFile();
        testGif = new ClassPathResource("image/sf.gif").getFile();

        imageService = new ImageServiceImpl();
        imageService.setImageDao(mockImageDao);
        imageService.setImageVersionDao(mockImageVersionDao);
        imageService.setAmazonS3Service(mockAmazonS3Service);
    }

    @Test
    public void resize_and_store_should_store_all_image_versions() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image expected = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);

        when(mockMultipartFile.getInputStream()).thenReturn(mockInputStream);
        when(ImageIO.read(mockMultipartFile.getInputStream())).thenReturn(mockBufferedImage);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageVersionDao, times(ImageType.values().length)).create(Matchers.<ImageVersion>anyObject());
    }

    @Test
    public void resize_and_store_should_store_all_image_versions_on_amazon() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image expected = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);

        when(mockMultipartFile.getInputStream()).thenReturn(mockInputStream);
        when(ImageIO.read(mockMultipartFile.getInputStream())).thenReturn(mockBufferedImage);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockAmazonS3Service, times(ImageType.values().length)).persistImage(Matchers.<ImageVersion>anyObject());
    }

    @Test
    public void resize_and_store_should_remove_all_old_image_versions() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);

        ImageVersion imageVersion = new ImageVersion("bytes".getBytes(), ImageType.NORMAL.toString(), image);
        ImageVersion imageVersion2 = new ImageVersion("bytes".getBytes(), ImageType.THUMBNAIL.toString(), image);
        ImageVersion imageVersion3 = new ImageVersion("bytes".getBytes(), ImageType.LARGE.toString(), image);

        List<ImageVersion> imageVersions = new ArrayList<ImageVersion>();
        imageVersions.add(imageVersion);
        imageVersions.add(imageVersion2);
        imageVersions.add(imageVersion3);

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
        Image expected = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);

        when(ImageIO.read(mockMultipartFile.getInputStream())).thenReturn(mockBufferedImage);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageDao).create(expected);
    }

    @Test
    public void resize_and_store_updates_existing_image() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);
        Image expected = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);
        expected.setId(1l);

        when(ImageIO.read(mockMultipartFile.getInputStream())).thenReturn(mockBufferedImage);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockImageDao).update(expected);
    }

    @Test(expected = FileNotFoundException.class)
    public void when_insert_resizeAndStoreImage_should_throw_exception_when_multipart_file_empty() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        when(mockMultipartFile.isEmpty()).thenReturn(true);

        Image expected = new Image("cow", "picture of a cow", null, true, mockCategory);

        imageService.resizeAndStoreImage(expected, mockMultipartFile);

        verify(mockMultipartFile).isEmpty();
        verify(mockImageDao, never()).update(expected);
        verify(mockImageDao, never()).create(expected);

    }

    @Test
    public void when_update_resizeAndStoreImage_should_not_update_versions_when_multipart_null() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);
        image.setId(1l);

        imageService.resizeAndStoreImage(image, null);

        verify(mockImageVersionDao, never());
    }

    @Test
    public void when_update_resizeAndStoreImage_should_not_manipulate_multipart_when_null() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        Image image = new Image("cow", "picture of a cow", "image/jpeg", true, mockCategory);
        image.setId(1l);

        imageService.resizeAndStoreImage(image, null);

        verify(mockMultipartFile, never());
    }

    @Test
    public void when_update_resizeAndStoreImage_should_not_set_mime_type_when_multipart_null() throws IOException {

        mockStatic(ImageIO.class);
        mockStatic(Scalr.class);

        // will be an update because id is not 0
        when(mockImage.getId()).thenReturn(1l);

        imageService.resizeAndStoreImage(mockImage, null);

        verify(mockImage, never()).setMimeType(Matchers.<String>anyObject());
    }

    @Test
    public void findByTypeAndImage_should_call_imageVersion_dao_and_amazon_service() throws IOException {

        when(mockImageVersion.getId()).thenReturn(1l);
        when(mockImageVersionDao.findByTypeAndImage(ImageType.NORMAL, mockImage)).thenReturn(mockImageVersion);

        imageService.findByTypeAndImage(ImageType.NORMAL, mockImage);

        verify(mockAmazonS3Service).getImage(String.valueOf(mockImageVersion.getId()));
        verify(mockImageVersionDao).findByTypeAndImage(ImageType.NORMAL, mockImage);

    }

}
