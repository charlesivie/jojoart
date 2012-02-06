package com.jojoart.web;

import com.jojoart.dao.CategoryDao;
import com.jojoart.dao.ImageDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 16/01/2012
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class PortfolioControllerTest {

    @Mock CategoryDao mockCategoryDao;
    @Mock ImageDao mockImageDao;
    PortfolioController portfolioController;
    List<Category> mockCategories = new LinkedList<Category>();
    List<Image> mockImages = new ArrayList<Image>();
    Category category;

    @Before
    public void setup(){

        category = new Category("Landscapes", "outdoors landscapes", true, true, null);
        category.setId(1l);

        mockImages.add(new Image("cow", "pictures of a cow", "image/jpg", true, category));
        mockImages.add(new Image("dog", "pictures of a cow", "image/jpg", true, category));
        mockImages.add(new Image("sheep", "pictures of a cow", "image/jpg", true, category));
        
        mockCategories.add(category);
        mockCategories.add(new Category("Portraits", "portraits", true, true, null));
        mockCategories.add(new Category("Life", "life", true, false, null));
        mockCategories.add(new Category("Abstract", "abstract", true, false, null));

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
        when(mockImageDao.listImagesByCategory(category)).thenReturn(mockImages);

        ModelAndView modelAndView = portfolioController.getImagesForCategory(category.getId());

        assertEquals(modelAndView.getModel().get("images"), mockImages);

        verify(mockImageDao).listImagesByCategory(category);
        verify(mockCategoryDao).read(Category.class, category.getId());

    }

    @Test
    public void getImagesForCategory_should_load_default_category_if_no_category_passed_through(){
        when(mockCategoryDao.findDefaultCategory()).thenReturn(category);
        when(mockImageDao.listImagesByCategory(category)).thenReturn(mockImages);
        when(mockCategoryDao.read(Category.class, category.getId())).thenReturn(category);

        ModelAndView modelAndView = portfolioController.getImagesForCategory(0l);

        assertEquals(modelAndView.getModel().get("images"), mockImages);

        verify(mockImageDao).listImagesByCategory(category);
        verify(mockCategoryDao).findDefaultCategory();
        verify(mockCategoryDao).read(Category.class, category.getId());
    }

    @Test
    public void get_categories_should_add_jspx_to_model(){
        ModelAndView modelAndView = portfolioController.getCategories();
        assertEquals(modelAndView.getViewName(), "category/list");
    }
    
    @Test
    public void get_index_should_load_index_jspx(){
        ModelAndView modelAndView = portfolioController.getIndex(0l);
        assertEquals(modelAndView.getViewName(), "index");
    }

    @Test
    public void get_index_should_add_category_id_to_model(){
        ModelAndView modelAndView = portfolioController.getIndex(007l);
        assertEquals(modelAndView.getModel().get("categoryId"), 007l);
    }

}
