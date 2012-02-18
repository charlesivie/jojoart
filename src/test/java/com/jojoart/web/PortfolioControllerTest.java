package com.jojoart.web;

import com.jojoart.dao.CategoryDao;
import com.jojoart.dao.ImageDao;
import com.jojoart.dao.StaticPageDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import com.jojoart.domain.StaticPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioControllerTest {

    @Mock CategoryDao mockCategoryDao;
    @Mock ImageDao mockImageDao;
    PortfolioController portfolioController;
    List<Category> mockCategories = new LinkedList<Category>();
    List<Image> mockImages = new ArrayList<Image>();
    Category category;
    @Mock private StaticPageDao mockStaticPageDao;

    @Before
    public void setup(){

        category = new Category("Landscapes", "outdoors landscapes", true, true);
        category.setId(1l);

        mockImages.add(new Image("cow", "pictures of a cow", "image/jpg", true, category));
        mockImages.add(new Image("dog", "pictures of a cow", "image/jpg", true, category));
        mockImages.add(new Image("sheep", "pictures of a cow", "image/jpg", true, category));
        
        mockCategories.add(category);
        mockCategories.add(new Category("Portraits", "portraits", true, true));
        mockCategories.add(new Category("Life", "life", true, false));
        mockCategories.add(new Category("Abstract", "abstract", true, false));

        portfolioController = new PortfolioController();
        portfolioController.setCategoryDao(mockCategoryDao);
        portfolioController.setImageDao(mockImageDao);
    }

    @Test
    public void get_categories_should_add_active_categories_to_model(){

        when(mockCategoryDao.getActiveCategoriesOrderByIsDefaultCategory()).thenReturn(mockCategories);
        ModelAndView modelAndView = portfolioController.getCategories();

        assertEquals(modelAndView.getModel().get("categories"), mockCategories);
        
        verify(mockCategoryDao).getActiveCategoriesOrderByIsDefaultCategory();
    }
    
    @Test
    public void getImagesForCategory_should_add_all_images_for_category_to_model(){
        when(mockCategoryDao.read(Category.class, category.getId())).thenReturn(category);
        when(mockImageDao.listActiveImagesByCategory(category)).thenReturn(mockImages);

        ModelAndView modelAndView = portfolioController.getImagesForCategory(category.getId());

        assertEquals(modelAndView.getModel().get("images"), mockImages);

        verify(mockImageDao).listActiveImagesByCategory(category);
        verify(mockCategoryDao).read(Category.class, category.getId());

    }

    @Test
    public void getImagesForCategory_should_load_default_category_if_no_category_passed_through(){
        when(mockCategoryDao.findDefaultCategory()).thenReturn(category);
        when(mockImageDao.listActiveImagesByCategory(category)).thenReturn(mockImages);
        when(mockCategoryDao.read(Category.class, category.getId())).thenReturn(category);

        ModelAndView modelAndView = portfolioController.getImagesForCategory(0l);

        assertEquals(modelAndView.getModel().get("images"), mockImages);

        verify(mockImageDao).listActiveImagesByCategory(category);
        verify(mockCategoryDao).findDefaultCategory();
        verify(mockCategoryDao).read(Category.class, category.getId());
    }

    @Test
    public void get_categories_should_add_jspx_to_model(){
        ModelAndView modelAndView = portfolioController.getCategories();
        assertEquals(modelAndView.getViewName(), "category/list");
    }

    @Test
    public void get_index_should_add_category_id_to_model(){
        when(mockCategoryDao.findCategoryByName("category")).thenReturn(category);
        ModelAndView modelAndView = portfolioController.getIndex("category");
        assertEquals(modelAndView.getModel().get("categoryId"), category.getId());
    }

    @Test
    public void getIndex_should_put_index_view_on_model(){
        ModelAndView modelAndView = portfolioController.getIndex();
        assertEquals("index", modelAndView.getViewName());
    }

}
