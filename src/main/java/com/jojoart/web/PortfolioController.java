package com.jojoart.web;

import com.jojoart.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 16/01/2012
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class PortfolioController {

    private CategoryDao categoryDao;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView getIndex(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getCategories(){

        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories", categoryDao.getActiveCategoriesOrderByIsDefaultCategory());

        return modelAndView;

    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
