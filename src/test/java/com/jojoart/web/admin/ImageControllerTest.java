package com.jojoart.web.admin;

import com.jojoart.dao.CategoryDaoImpl;
import com.jojoart.dao.ImageDaoImpl;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 09/12/2011
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {

    @Mock
    private ImageDaoImpl imageDataDao;
    @Mock
    private CategoryDaoImpl categoryDao;
    @Mock
    private Category mockCategory;
    private ImageController imageController;
    @Mock
    private ImageService imageService;
    @Mock
    private MultipartFile multipartFile;
    @Mock
    private ImageVersionDao imageVersionDao;

    @Before
    public void setup(){
        imageController = new ImageController();
        imageController.setImageDao(imageDataDao);
        imageController.setImageService(imageService);
        imageController.setCategoryDao(categoryDao);
        imageController.setImageVersionDao(imageVersionDao);
    }

    @Test
    public void get_edit_should_load_image_versions() {
        Image image = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        ImageVersion imageVersion = new ImageVersion("whatever".getBytes(), ImageType.NORMAL.getMaxSize(), image);
        ImageVersion imageVersion2 = new ImageVersion("whatever".getBytes(), ImageType.THUMBNAIL.getMaxSize(), image);

        List<ImageVersion> imageVersions = new ArrayList<ImageVersion>();
        imageVersions.add(imageVersion);
        imageVersions.add(imageVersion2);
        
        when(imageDataDao.read(Image.class, 1l)).thenReturn(image);
        when(imageVersionDao.getAllImageVersions(image)).thenReturn(imageVersions);

        ModelAndView modelAndView = imageController.edit(1l);
        
        assertEquals(imageVersions, modelAndView.getModelMap().get("imageVersions"));

        verify(imageDataDao).read(Image.class, 1l);
        verify(imageVersionDao).getAllImageVersions(image);

    }

    @Test
    public void listReturnsModelAndViewWithList() throws Exception {

        List<Image> images = new ArrayList<Image>();

        images.add(new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory));
        images.add(new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory));

        when(imageDataDao.list(Image.class)).thenReturn(images);

        ModelAndView modelAndView = imageController.list();

        assertEquals(images, modelAndView.getModelMap().get("images"));

        verify(imageDataDao).list(Image.class);

    }

    @Test
    public void getEditShouldLoadCategoryListOnModel(){

        List<Category> categories = new ArrayList<Category>();
        for(int i = 1; i <10; i++){
            categories.add(new Category("john"+i, "desc", true, false, null));
        }

        Image image = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        when(imageDataDao.read(Image.class, 1l)).thenReturn(image);
        when(categoryDao.list(Category.class)).thenReturn(categories);

        ModelAndView modelAndView = imageController.edit(1l);

        assertEquals(categories, modelAndView.getModelMap().get("categories"));
    }

    @Test
    public void postEditShouldLoadCategoryListOnModel() throws IOException {
        
        List<Category> categories = new ArrayList<Category>();
        for(int i = 1; i <10; i++){
            categories.add(new Category("john"+i, "desc", true, false, null));
        }

        Image image = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        when(imageDataDao.create(image)).thenReturn(image);
        when(categoryDao.list(Category.class)).thenReturn(categories);

        ModelAndView modelAndView = imageController.edit(image, 0, multipartFile);

        assertEquals(categories, modelAndView.getModelMap().get("categories"));

    }

    @Test
    public void getEditReturnsModelAndViewWithImage() throws Exception {

        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        when(imageDataDao.read(Image.class, 1l)).thenReturn(expected);

        ModelAndView modelAndView = imageController.edit(1l);

        assertEquals(expected, modelAndView.getModelMap().get("image"));

        verify(imageDataDao).read(Image.class, 1l);

    }
    @Test
    public void postEditResizesAndStoresImage() throws IOException {
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);
        expected.setId(1l);

        ModelAndView modelAndView = imageController.edit(expected, 1l, multipartFile);

        assertEquals(expected, modelAndView.getModelMap().get("image"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(imageService).resizeAndStoreImage(expected, multipartFile);
    }
}
