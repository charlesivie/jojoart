package com.jojoart.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 08:50
 * To change this template use File | Settings | File Templates.
 */

public class CategoryTest {

    Image image;
    Image image2;
    
    @Before
    public void setup(){
        image = new Image("image", "pictures of a cow", "image/jpg", true, null);
        image2 = new Image("image 2", "pictures of a dog", "image/jpg", true, null);
    }

    @Test
    public void equalsReturnsTrueWhenEqual() {

        Category category1 = new Category("john", "desc", true, false, null);
        Category category2 = new Category("john", "desc", true, false, null);

        assertTrue(category1.equals(category2));

    }

    @Test
    public void equalsReturnsFalseWhenNotEqual() {

        Category category1 = new Category("john", "desc", true, false, null);
        Category category2 = new Category("bob", "desc", true, false, null);

        assertFalse(category1.equals(category2));

    }

    @Test
    public void equalsReturnsFalseWhenImageNotEqual() {

        Category category1 = new Category("john", "desc", true, false, image);
        Category category2 = new Category("john", "desc", true, false, image2);

        assertFalse(category1.equals(category2));

    }

    @Test
    public void hashCodeShouldBeEqual() {

        Category category1 = new Category("john", "desc", true, false, null);
        Category category2 = new Category("john", "desc", true, false, null);

        assertEquals(category1.hashCode(), category2.hashCode());

    }

    @Test
    public void hashCodeShouldNotBeEqual() {

        Category category1 = new Category("john", "desc", true, false, null);
        Category category2 = new Category("bob", "desc", true, false, null);

        assertNotSame(category1.hashCode(), category2.hashCode());

    }

    @Test
    public void hashCodeShouldNotBeEqualWhenImagesAreDifferent() {

        Category category1 = new Category("john", "desc", true, false, image);
        Category category2 = new Category("bob", "desc", true, false, image2);

        assertNotSame(category1.hashCode(), category2.hashCode());

    }

}
