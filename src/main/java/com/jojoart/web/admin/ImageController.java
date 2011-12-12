package com.jojoart.web.admin;

import com.jojoart.dao.CategoryDao;
import com.jojoart.dao.CategoryDaoImpl;
import com.jojoart.dao.ImageDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 09/12/2011
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class ImageController {

    private ImageDao imageDao;
    private CategoryDao categoryDao;

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView("/image/list");
        modelAndView.addObject("images", imageDao.list(Image.class));

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ModelAndView edit(@PathVariable long id) {

        Image image = new Image();

        if(id>0) {
            image = imageDao.read(Image.class, id);
        }

        ModelAndView modelAndView = new ModelAndView("image/edit");
        modelAndView.addObject("categories", categoryDao.list(Category.class));
        modelAndView.addObject("image", image);

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = POST)
    public ModelAndView edit(@ModelAttribute("image") Image image, @PathVariable long id) {

        if(image.getId()>0) {
            imageDao.update(image);
        }
        else {
            imageDao.create(image);
        }

        ModelAndView modelAndView = new ModelAndView("image/edit");
        modelAndView.addObject("categories", categoryDao.list(Category.class));
        modelAndView.addObject("image", image);
        modelAndView.addObject("saved", true);

        return modelAndView;
    }


    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

}
