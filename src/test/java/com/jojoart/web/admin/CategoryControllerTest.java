package com.jojoart.web.admin;

import com.jojoart.dao.CategoryDaoImpl;
import com.jojoart.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    CategoryDaoImpl categoryDao;
    CategoryController categoryController;

    @Before
    public void setup(){
        categoryController = new CategoryController();
        categoryController.setCategoryDao(categoryDao);
    }

    @Test
    public void testListReturnsModelAndViewWithList() throws Exception {

        List<Category> mockCategories = new ArrayList<Category>();

        mockCategories.add(new Category("john", "desc", true, false));
        mockCategories.add(new Category("john", "desc", true, false));

        when(categoryDao.list(Category.class)).thenReturn(mockCategories);

        ModelAndView modelAndView = categoryController.list();

        assertEquals(mockCategories, modelAndView.getModelMap().get("categories"));

        verify(categoryDao).list(Category.class);

    }

    @Test
    public void testEditReturnsModelAndViewWithCategory() throws Exception {

        Category expected = new Category("bob", "jones", true, false);

        when(categoryDao.read(Category.class, 1l)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(1l);

        assertEquals(expected, modelAndView.getModelMap().get("category"));

        verify(categoryDao).read(Category.class, 1l);

    }

    @Test
    public void testProcessSubmitStoresNewCategory(){
        Category expected = new Category("bob", "jones", true, false);

        when(categoryDao.create(expected)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(expected, 0);

        assertEquals(expected, modelAndView.getModelMap().get("category"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(categoryDao).create(expected);
    }

    @Test
    public void testProcessSubmitUpdatesCategory(){
        Category expected = new Category("bob", "jones", true, false);
        expected.setId(1l);

        when(categoryDao.update(expected)).thenReturn(expected);

        ModelAndView modelAndView = categoryController.edit(expected, 1l);

        assertEquals(expected, modelAndView.getModelMap().get("category"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(categoryDao).update(expected);
    }

}
