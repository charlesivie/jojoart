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
    public void set_path_should_url_encode_path(){
        StaticPage staticPage1 = new StaticPage("admin then whatever", "html content", true);
        assertEquals("admin+then+whatever", staticPage1.getPath());
    }


}
