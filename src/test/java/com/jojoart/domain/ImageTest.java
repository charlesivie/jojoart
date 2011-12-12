package com.jojoart.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 08:52
 * To change this template use File | Settings | File Templates.
 */
public class ImageTest {

    Category category;
    Category category2;

    @Before
    public void setup() {
        category = new Category("john", "desc", true, false);
        category2 = new Category("bob", "desc", true, false);
    }

    @Test
    public void equalsReturnsTrueWhenEqual() {

        Image a1 = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        Image a2 = new Image("image image", "pictures of a cow", "image/jpg", true, category);

        assertTrue(a1.equals(a2));

    }

    @Test
    public void equalsReturnsFalseWhenNotEqual() {

        Image a1 = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        Image a2 = new Image("image image", "pictures of a dog", "image/jpg", true, category);

        assertFalse(a1.equals(a2));

    }

    @Test
    public void hashCodeShouldBeEqual() {
        Image a1 = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        Image a2 = new Image("image image", "pictures of a cow", "image/jpg", true, category);

        assertEquals(a1.hashCode(), a2.hashCode());
    }


    @Test
    public void hashCodeShouldNotBeEqual() {
        Image a1 = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        Image a2 = new Image("image image", "pictures of a dog", "image/jpg", true, category);

        assertNotSame(a1.hashCode(), a2.hashCode());
    }


    @Test
    public void hashCodeShouldNotBeEqualWithDifferentCategories() {
        Image a1 = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        Image a2 = new Image("image image", "pictures of a cow", "image/jpg", true, category2);

        assertNotSame(a1.hashCode(), a2.hashCode());
    }

    @Test
    public void shouldNotBeEqualWithDifferentCategories() {
        Image a1 = new Image("image image", "pictures of a cow", "image/jpg", true, category);
        Image a2 = new Image("image image", "pictures of a cow", "image/jpg", true, category2);

        assertFalse(a1.equals(a2));
    }

}
