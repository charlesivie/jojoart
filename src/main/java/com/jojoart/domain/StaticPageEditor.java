package com.jojoart.domain;

import java.beans.PropertyEditorSupport;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/02/2012
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class StaticPageEditor extends PropertyEditorSupport {

    public void setAsText(String path) {
        setValue(new StaticPage(path));
    }

}