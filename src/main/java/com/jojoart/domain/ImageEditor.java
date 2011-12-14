package com.jojoart.domain;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */

import java.beans.PropertyEditorSupport;

public class ImageEditor extends PropertyEditorSupport {

    public void setAsText(String id) {
        setValue(new Image(Long.parseLong(id)));
    }

}
