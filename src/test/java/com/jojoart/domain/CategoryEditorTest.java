package com.jojoart.domain;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 13/12/2011
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */

public class CategoryEditorTest {

    private CategoryEditor categoryEditor = new CategoryEditor();
    
    @Test
    public void setAsTextShouldGetCategory() {
        categoryEditor.setAsText(String.valueOf(1));
        Category category = (Category)categoryEditor.getValue();
        assertEquals(1l, category.getId());
    }
}
