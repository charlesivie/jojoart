package com.jojoart.web;

import com.jojoart.dao.StaticPageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/static")
public class StaticPageController {

    @Autowired
    private StaticPageDao staticPageDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getList(){
        ModelAndView modelAndView = new ModelAndView("static/list");
        modelAndView.addObject("staticPages", staticPageDao.listActive());
        return modelAndView;
    }

    @RequestMapping(value = "/{path}", method = RequestMethod.GET)
    public ModelAndView getStaticPage(@PathVariable("path") String path){
        ModelAndView modelAndView = new ModelAndView("static/page");
        modelAndView.addObject("staticPage", staticPageDao.findActiveByPath(path));
        return modelAndView;
    }


}
