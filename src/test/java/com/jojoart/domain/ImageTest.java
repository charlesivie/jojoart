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
public class ImageTest {

    ImageGroup imageGroup;

    @Before
    public void setup(){
        Category category = new Category("Landscapes", "outdoors landscapes", true, true);
        imageGroup = new ImageGroup("name", "description", true, true, category);
    }

    @Test
    public void equalsReturnsTrueWhenEqual() {


        Image image1 = new Image(
                1,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);
        Image image2 = new Image(
                1,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);

        assertTrue(image1.equals(image2));

    }

    @Test
    public void equalsReturnsFalseWhenNotEqual() {


        Image image1 = new Image(
                1,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);
        Image image2 = new Image(
                2,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);

        assertFalse(image1.equals(image2));

    }

    @Test
    public void hashCodeShouldBeEqual() {


        Image image1 = new Image(
                1,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);
        Image image2 = new Image(
                1,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);

        assertEquals(image1.hashCode(), image2.hashCode());

    }

    @Test
    public void hashCodeShouldNotBeEqual() {


        Image image1 = new Image(
                1,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);
        Image image2 = new Image(
                2,
                "mimetype",
                "something".getBytes(),
                "name",
                "description",
                true,
                true,
                imageGroup);

        assertNotSame(image1.hashCode(), image2.hashCode());

    }
}
