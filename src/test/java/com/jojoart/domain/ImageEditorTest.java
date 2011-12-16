package com.jojoart.domain;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */

public class ImageEditorTest {

    private ImageEditor imageEditor = new ImageEditor();

    @Test
    public void setAsTextShouldSetImage() {
        imageEditor.setAsText(String.valueOf(1));
        Image image = (Image)imageEditor.getValue();
        assertEquals(1l, image.getId());
    }
}
