package com.jojoart.web;

import com.jojoart.dao.CategoryDao;
import com.jojoart.domain.Category;
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
    PortfolioController portfolioController;
    List<Category> mockCategories = new LinkedList<Category>();

    @Before
    public void setup(){

        mockCategories.add(new Category("Landscapes", "outdoors landscapes", true, true, null));
        mockCategories.add(new Category("Portraits", "portraits", true, true, null));
        mockCategories.add(new Category("Life", "life", true, false, null));
        mockCategories.add(new Category("Abstract", "abstract", true, false, null));

        portfolioController = new PortfolioController();
        portfolioController.setCategoryDao(mockCategoryDao);
    }

    @Test
    public void get_categories_should_add_active_categories_to_model(){

        when(mockCategoryDao.getActiveCategoriesOrderByIsDefaultCategory()).thenReturn(mockCategories);
        ModelAndView modelAndView = portfolioController.getCategories();

        assertEquals(modelAndView.getModel().get("categories"), mockCategories);
        
        verify(mockCategoryDao).getActiveCategoriesOrderByIsDefaultCategory();
    }

    @Test
    public void get_categories_should_add_jspx_to_model(){
        ModelAndView modelAndView = portfolioController.getCategories();
        assertEquals(modelAndView.getViewName(), "category/list");
    }
    
    @Test
    public void get_index_should_load_index_jspx(){
        ModelAndView modelAndView = portfolioController.getIndex();
        assertEquals(modelAndView.getViewName(), "index");
    }

}
