package com.jojoart.web.admin;

import com.jojoart.dao.StaticPageDao;
import com.jojoart.domain.StaticPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 12/02/2012
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/staticpage")
public class StaticPageController {

    @Autowired
    private StaticPageDao staticPageDao;

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView("admin/staticpage/list");
        modelAndView.addObject("staticpages", staticPageDao.list(StaticPage.class));

        return modelAndView;
    }


}
