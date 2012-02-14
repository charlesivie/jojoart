package com.jojoart.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 12/02/2012
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class StaticPageTest {

   @Test
    public void equalsReturnsTrueWhenEqual() {

       StaticPage staticPage1 = new StaticPage("admin", "html content", true);

        assertTrue(staticPage1.equals(staticPage1));

    }

    @Test
    public void equalsReturnsFalseWhenNotEqual() {

        StaticPage staticPage1 = new StaticPage("admin", "html content", true);
        StaticPage staticPage2 = new StaticPage("admin", "html content 2", true);

        assertFalse(staticPage1.equals(staticPage2));

    }

    @Test
    public void hashCodeShouldBeEqual() {

        StaticPage staticPage1 = new StaticPage("admin", "html content", true);
        assertEquals(staticPage1.hashCode(), staticPage1.hashCode());

    }

    @Test
    public void hashCodeShouldNotBeEqual() {

        StaticPage staticPage1 = new StaticPage("admin", "html content", true);
        StaticPage staticPage2 = new StaticPage("contact", "html content 2", false);

        assertNotSame(staticPage1.hashCode(), staticPage2.hashCode());

    }

    @Test
    public void set_path_should_url_encode_path(){
        StaticPage staticPage1 = new StaticPage("admin then whatever", "html content", true);
        assertEquals("admin+then+whatever", staticPage1.getPath());
    }

}
