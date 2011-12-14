package com.jojoart.domain;

import java.beans.PropertyEditorSupport;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 13/12/2011
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
public class CategoryEditor extends PropertyEditorSupport {
    
    public void setAsText(String id) {
        setValue(new Category(Long.parseLong(id)));
    }
    
}