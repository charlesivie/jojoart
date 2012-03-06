package com.jojoart.service;

import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public interface ImageService {
    void resizeAndStoreImage(Image image, MultipartFile multipartFile) throws IOException;
    ImageVersion findByTypeAndImage(ImageType imageType, Image image) throws IOException;
}
