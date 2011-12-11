package com.jojoart.web.admin;

import com.jojoart.dao.ImageDataDaoImpl;
import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
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
 * Date: 09/12/2011
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class ImageDataControllerTest {

    @Mock
    private ImageDataDaoImpl imageDataDao;
    @Mock
    private Category mockCategory;
    private ImageDataController imageDataController;

    @Before
    public void setup(){
        imageDataController = new ImageDataController();
        imageDataController.setImageDataDao(imageDataDao);
    }

    @Test
    public void testListReturnsModelAndViewWithList() throws Exception {

        List<Image> images = new ArrayList<Image>();

        images.add(new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory));
        images.add(new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory));

        when(imageDataDao.list(Image.class)).thenReturn(images);

        ModelAndView modelAndView = imageDataController.list();

        assertEquals(images, modelAndView.getModelMap().get("images"));

        verify(imageDataDao).list(Image.class);

    }

    @Test
    public void testEditReturnsModelAndViewWithImageGroup() throws Exception {

        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        when(imageDataDao.read(Image.class, 1l)).thenReturn(expected);

        ModelAndView modelAndView = imageDataController.edit(1l);

        assertEquals(expected, modelAndView.getModelMap().get("image"));

        verify(imageDataDao).read(Image.class, 1l);

    }

    @Test
    public void testProcessSubmitStoresNewImageGroup(){
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);

        when(imageDataDao.create(expected)).thenReturn(expected);

        ModelAndView modelAndView = imageDataController.edit(expected, 0);

        assertEquals(expected, modelAndView.getModelMap().get("image"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(imageDataDao).create(expected);
    }

    @Test
    public void testProcessSubmitUpdatesImageGroup(){
        Image expected = new Image("cow", "piture of a cow", "image/jpeg", true, mockCategory);
        expected.setId(1l);

        when(imageDataDao.update(expected)).thenReturn(expected);

        ModelAndView modelAndView = imageDataController.edit(expected, 1l);

        assertEquals(expected, modelAndView.getModelMap().get("image"));
        assertEquals(true, modelAndView.getModelMap().get("saved"));

        verify(imageDataDao).update(expected);
    }
}
