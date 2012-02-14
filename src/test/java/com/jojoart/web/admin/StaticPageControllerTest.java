package com.jojoart.web.admin;

import com.jojoart.dao.StaticPageDao;
import com.jojoart.domain.StaticPage;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/02/2012
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class StaticPageControllerTest {

    @InjectMocks
    private StaticPageController staticPageController = new StaticPageController();

    @Mock
    private StaticPageDao staticPageDao;

    @Test
    public void list_should_add_list_of_static_Pages_to_model(){

        List<StaticPage> staticPages = new ArrayList<StaticPage>();

        staticPages.add(new StaticPage("admin", "html content", true));
        staticPages.add(new StaticPage("poetry", "html content", true));

        when(staticPageDao.list(StaticPage.class)).thenReturn(staticPages);

        ModelAndView modelAndView = staticPageController.list();

        assertEquals(staticPages, modelAndView.getModelMap().get("staticpages"));

        verify(staticPageDao).list(StaticPage.class);
    }
    
    @Test
    @Ignore("to be implemented")
    public void edit_post_should_update(){
    }

    @Test
    @Ignore("to be implemented")
    public void edit_post_should_insert(){

    }

    @Test
    @Ignore("to be implemented")
    public void edit_get_should_put_staticPage_on_model(){
    }


}
