package com.jojoart.web;

import com.jojoart.dao.CategoryDao;
import com.jojoart.dao.ImageDao;
import com.jojoart.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 16/01/2012
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PortfolioController {

    private CategoryDao categoryDao;
    private ImageDao imageDao;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView getIndex(@RequestParam(value = "categoryId", required = false) Long categoryId ) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("categoryId", categoryId);
        return mav;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getCategories() {

        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories", categoryDao.getActiveCategoriesOrderByIsDefaultCategory());

        return modelAndView;

    }

    @RequestMapping(value = "/images/{categoryId}")
    public ModelAndView getImagesForCategory(@PathVariable("categoryId") Long categoryId) {

        if(categoryId==null || categoryId <= 0){
            categoryId = categoryDao.findDefaultCategory().getId();
        }

        ModelAndView modelAndView = new ModelAndView("image/list");

        modelAndView.addObject(
                "images",
                imageDao.listImagesByCategory(categoryDao.read(Category.class, categoryId))
        );

        return modelAndView;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
}
