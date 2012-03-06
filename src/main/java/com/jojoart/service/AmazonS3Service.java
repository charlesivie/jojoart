package com.jojoart.service;

import com.jojoart.domain.ImageVersion;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 05/03/2012
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public interface AmazonS3Service {

    byte[] getImage(String id) throws IOException;
    void persistImage(ImageVersion imageVersion);

}
