package com.jojoart.domain;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 10/12/2011
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public enum ImageType {

    LARGE(1000),
    NORMAL(500),
    THUMBNAIL(75);

    private int maxSize;

    private ImageType(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

}
