package com.jojoart.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 08:58
 * To change this template use File | Settings | File Templates.
 */
public class ImageVersionTest {

    Image image;

    @Before
    public void setup(){
        Category category = new Category("Landscapes", "outdoors landscapes", true, true);
        image = new Image("name", "description", "image/jpg", true, category);
    }

    @Test
    public void equalsReturnsTrueWhenEqual() {


        ImageVersion imageVersion1 = new ImageVersion(
                "something".getBytes(),
                ImageType.NORMAL.toString(),
                image);

        ImageVersion imageVersion2 = new ImageVersion(
                "something".getBytes(),
                ImageType.NORMAL.toString(),
                image);

        assertTrue(imageVersion1.equals(imageVersion2));

    }

    @Test
    public void equalsReturnsFalseWhenNotEqual() {


        ImageVersion imageVersion1 = new ImageVersion(
                "something".getBytes(),
                ImageType.NORMAL.toString(),
                image);
        ImageVersion imageVersion2 = new ImageVersion(
                "something".getBytes(),
                ImageType.THUMBNAIL.toString(),
                image);

        assertFalse(imageVersion1.equals(imageVersion2));

    }

    @Test
    public void hashCodeShouldBeEqual() {

        ImageVersion imageVersion1 = new ImageVersion(
                "something".getBytes(),
                ImageType.NORMAL.toString(),
                image);

        ImageVersion imageVersion2 = new ImageVersion(
                "something".getBytes(),
                ImageType.NORMAL.toString(),
                image);

        assertEquals(imageVersion1.hashCode(), imageVersion2.hashCode());

    }

    @Test
    public void hashCodeShouldNotBeEqual() {

        ImageVersion imageVersion1 = new ImageVersion(
                "something".getBytes(),
                ImageType.NORMAL.toString(),
                image);
        ImageVersion imageVersion2 = new ImageVersion(
                "something".getBytes(),
                ImageType.THUMBNAIL.toString(),
                image);

        assertNotSame(imageVersion1.hashCode(), imageVersion2.hashCode());

    }
}
