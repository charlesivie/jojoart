package com.jojoart.web;

import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import com.jojoart.service.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 19/12/2011
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageStreamControllerTest {

    ImageStreamController imageStreamController;
    @Mock ImageService imageService;
    @Mock HttpServletResponse httpServletResponse;
    @Mock ServletOutputStream servletOutputStream;
    Image image;
    ImageVersion imageVersion;
    ImageVersion imageVersion2;
    Category category;
    Long imageId = 1l;

    @Before
    public void setUp() throws Exception {

        category = new Category("john", "desc", true, false);
        image = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        image.setId(imageId);

        imageVersion = new ImageVersion(
                "thumb".getBytes(),
                ImageType.THUMBNAIL.toString(),
                image);
        imageVersion.setId(1l);

        imageVersion2 = new ImageVersion(
                "normal".getBytes(),
                ImageType.NORMAL.toString(),
                image);
        imageVersion2.setId(2l);

        imageStreamController = new ImageStreamController();
        imageStreamController.setImageService(imageService);
    }

    @Test
    public void stream_should_get_image_by_id_and_type() throws Exception {
        when(imageService.findByTypeAndImage(ImageType.NORMAL, new Image(image.getId()))).thenReturn(imageVersion);
        when(httpServletResponse.getOutputStream()).thenReturn(servletOutputStream);

        imageStreamController.stream(httpServletResponse, imageId, ImageType.NORMAL.toString());

        verify(imageService).findByTypeAndImage(ImageType.NORMAL, new Image(image.getId()));
    }

    @Test
    public void stream_should_set_correct_response() throws IOException {

        when(imageService.findByTypeAndImage(ImageType.NORMAL, new Image(image.getId()))).thenReturn(imageVersion);
        when(httpServletResponse.getOutputStream()).thenReturn(servletOutputStream);

        imageStreamController.stream(httpServletResponse, imageId, ImageType.NORMAL.toString());

        verify(httpServletResponse).setContentType(imageVersion.getImage().getMimeType());
        verify(httpServletResponse).setContentLength(imageVersion.getImageBlob().length);

        verify(httpServletResponse, times(2)).getOutputStream();
        verify(servletOutputStream).write(imageVersion.getImageBlob());
        verify(servletOutputStream).flush();
    }

}
