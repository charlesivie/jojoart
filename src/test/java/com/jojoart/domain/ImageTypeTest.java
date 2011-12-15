package com.jojoart.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 11/12/2011
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */

public class ImageTypeTest {

    @Test
    public void getWidthShouldReturnNormalSize(){
        int actualWidth = ImageType.NORMAL.getMaxSize();
        int expected = 500;
        assertEquals(expected, actualWidth);
    }

    @Test
    public void getWidthShouldReturnThumbnailSize(){
        int actualWidth = ImageType.THUMBNAIL.getMaxSize();
        int expected = 100;
        assertEquals(expected, actualWidth);
    }
}
