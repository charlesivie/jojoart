package com.jojoart.web;

import com.jojoart.dao.StaticPageDao;
import com.jojoart.domain.StaticPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
 * Date: 18/02/2012
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class StaticPageControllerTest {

    @InjectMocks
    private StaticPageController staticPageController = new StaticPageController();

    @Mock
    private StaticPageDao mockStaticPageDao;

    @Test
    public void getList_should_put_static_pages_on_model(){

        StaticPage staticPage = new StaticPage("about", "content", true);
        List<StaticPage> staticPages = new ArrayList<StaticPage>();
        staticPages.add(staticPage);

        when(mockStaticPageDao.listActive()).thenReturn(staticPages);

        ModelAndView modelAndView = staticPageController.getList();

        assertEquals(staticPages, modelAndView.getModel().get("staticPages"));

        verify(mockStaticPageDao).listActive();
    }

    @Test
    public void getStaticPage_should_put_static_pages_on_model(){

        StaticPage staticPage = new StaticPage("about", "content", true);

        when(mockStaticPageDao.findActiveByPath("about")).thenReturn(staticPage);

        ModelAndView modelAndView = staticPageController.getStaticPage("about");

        assertEquals(staticPage, modelAndView.getModel().get("staticPage"));

        verify(mockStaticPageDao).findActiveByPath("about");
    }


}
