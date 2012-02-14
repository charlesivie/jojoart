package com.jojoart.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/02/2012
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class StaticPageEditorTest {

    private StaticPageEditor editor = new StaticPageEditor();

    @Test
    public void setAsTextShouldSetImage() {
        editor.setAsText("about");
        StaticPage staticPage = (StaticPage) editor.getValue();
        assertEquals("about", staticPage.getPath());
    }
}
