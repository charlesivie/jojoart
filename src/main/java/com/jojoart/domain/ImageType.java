package com.jojoart.domain;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 10/12/2011
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public enum ImageType {
    
    NORMAL(500),
    THUMBNAIL(100);

    private int width;

    private ImageType(int i) {
        width = i;
    }

    public int getWidth() {
        return width;
    }

}
