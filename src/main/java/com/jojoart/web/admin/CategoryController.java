package com.jojoart.web.admin;

import com.jojoart.dao.CategoryDao;
import com.jojoart.dao.ImageDao;
import com.jojoart.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CategoryController {

    private CategoryDao categoryDao;
    private ImageDao imageDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categoryDao.list(Category.class));

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable long id) {

        Category category = new Category();

        if(id>0) {
            category = categoryDao.read(Category.class, id);
        }

        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("category") Category category, @PathVariable long id) {

        if(category.getId()>0) {
            categoryDao.update(category);
        }
        else {
            categoryDao.create(category);
        }

        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("saved", true);

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
