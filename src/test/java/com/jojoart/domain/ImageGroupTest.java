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
public class ImageGroupTest {

    Category category;

    @Before
    public void setup() {
        category = new Category("john", "desc", true, false);
    }

    @Test
    public void equalsReturnsTrueWhenEqual() {

        ImageGroup group1 = new ImageGroup("image imageGroup", "pictures of a cow", true, true, category);
        ImageGroup group2 = new ImageGroup("image imageGroup", "pictures of a cow", true, true, category);

        assertTrue(group1.equals(group2));

    }

    @Test
    public void equalsReturnsFalseWhenNotEqual() {

        ImageGroup group1 = new ImageGroup("image imageGroup", "pictures of a cow", true, true, category);
        ImageGroup group2 = new ImageGroup("image imageGroup", "pictures of a dog", true, true, category);

        assertFalse(group1.equals(group2));

    }

    @Test
    public void hashCodeShouldBeEqual() {
        ImageGroup group1 = new ImageGroup("image imageGroup", "pictures of a cow", true, true, category);
        ImageGroup group2 = new ImageGroup("image imageGroup", "pictures of a cow", true, true, category);

        assertEquals(group1.hashCode(), group2.hashCode());
    }


    @Test
    public void hashCodeShouldNotBeEqual() {
        ImageGroup group1 = new ImageGroup("image imageGroup", "pictures of a cow", true, true, category);
        ImageGroup group2 = new ImageGroup("image imageGroup", "pictures of a dog", true, true, category);

        assertNotSame(group1.hashCode(), group2.hashCode());
    }

}
