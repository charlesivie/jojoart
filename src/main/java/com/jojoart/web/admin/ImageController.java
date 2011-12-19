package com.jojoart.web.admin;

import com.jojoart.dao.CategoryDao;
import com.jojoart.dao.ImageDao;
import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageVersion;
import com.jojoart.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 09/12/2011
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/admin/image")
public class ImageController {

    private ImageDao imageDao;
    private CategoryDao categoryDao;
    private ImageService imageService;
    private ImageVersionDao imageVersionDao;


    @RequestMapping(value = "/list", method = GET)
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView("admin/image/list");
        modelAndView.addObject("images", imageDao.list(Image.class));

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ModelAndView edit(@PathVariable long id) {

        Image image = new Image();
        List<ImageVersion> imageVersions = new ArrayList<ImageVersion>();

        if(id>0) {
            image = imageDao.read(Image.class, id);
            imageVersions = imageVersionDao.getAllImageVersions(image);
        }

        ModelAndView modelAndView = new ModelAndView("admin/image/edit");
        modelAndView.addObject("categories", categoryDao.list(Category.class));
        modelAndView.addObject("image", image);
        modelAndView.addObject("imageVersions", imageVersions);

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = POST)
    public ModelAndView edit(
            @ModelAttribute("image") Image image,
            @PathVariable long id,
            @RequestParam MultipartFile file) throws IOException {

        imageService.resizeAndStoreImage(image, file);

        ModelAndView modelAndView = new ModelAndView("admin/image/edit");
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

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setImageVersionDao(ImageVersionDao imageVersionDao) {
        this.imageVersionDao = imageVersionDao;
    }
}
