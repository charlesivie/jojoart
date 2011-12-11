package com.jojoart.web.admin;

import com.jojoart.dao.CategoryDaoImpl;
import com.jojoart.dao.ImageDaoImpl;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryDaoImpl mockCategoryDao;
    @Mock
    private ImageDaoImpl mockImageDao;
    private CategoryController categoryController;

    @Before
    public void setup(){
        categoryController = new CategoryController();
        categoryController.setCategoryDao(mockCategoryDao);
        categoryController.setImageDao(mockImageDao);
    }

    @Test
    public void editShouldProvideCategoryWithImageToModelAndView(){

        Category expected = new Category("bob", "jones", true, false);
        Image expectedImage = new Image("image", "desc", "image/jpg", true, expected);
        expected.setImage(expectedImage);

        when(mockCategoryDao.read(Category.class, 1l)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(1l);

        assertEquals(expected, modelAndView.getModelMap().get("category"));

        verify(mockCategoryDao).read(Category.class, 1l);

    }

    @Test
    public void editShouldProvideImageListToModelAndView(){

        Category expected = new Category("bob", "jones", true, false);
        Image expectedImage = new Image("image", "desc", "image/jpg", true, expected);
        Image expectedImage2 = new Image("image", "desc", "image/jpg", true, expected);
        Image expectedImage3 = new Image("image", "desc", "image/jpg", true, expected);
        List<Image> expectedImages = new ArrayList<Image>();
        expectedImages.add(expectedImage);
        expectedImages.add(expectedImage2);
        expectedImages.add(expectedImage3);

        when(mockCategoryDao.read(Category.class, 1l)).thenReturn(expected);
        when(mockImageDao.listImagesForCategory(expected)).thenReturn(expectedImages);

        ModelAndView modelAndView = categoryController.edit(1l);

        assertEquals(expectedImages, modelAndView.getModelMap().get("images"));

        verify(mockImageDao).listImagesForCategory(expected);

    }

    @Test
    public void testListReturnsModelAndViewWithList() throws Exception {

        List<Category> mockCategories = new ArrayList<Category>();

        mockCategories.add(new Category("john", "desc", true, false));
        mockCategories.add(new Category("john", "desc", true, false));

        when(mockCategoryDao.list(Category.class)).thenReturn(mockCategories);

        ModelAndView modelAndView = categoryController.list();

        assertEquals(mockCategories, modelAndView.getModelMap().get("categories"));

        verify(mockCategoryDao).list(Category.class);

    }

    @Test
    public void testEditReturnsModelAndViewWithCategory() throws Exception {

        Category expected = new Category("bob", "jones", true, false);

        when(mockCategoryDao.read(Category.class, 1l)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(1l);

        assertEquals(expected, modelAndView.getModelMap().get("category"));

        verify(mockCategoryDao).read(Category.class, 1l);

    }

    @Test
    public void testProcessSubmitStoresNewCategory(){
        Category expected = new Category("bob", "jones", true, false);

        when(mockCategoryDao.create(expected)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(expected, 0);

        assertEquals(expected, modelAndView.getModelMap().get("category"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(mockCategoryDao).create(expected);
    }

    @Test
    public void testProcessSubmitUpdatesCategory(){
        Category expected = new Category("bob", "jones", true, false);
        expected.setId(1l);

        when(mockCategoryDao.update(expected)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(expected, 1l);

        assertEquals(expected, modelAndView.getModelMap().get("category"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(mockCategoryDao).update(expected);
    }

}
