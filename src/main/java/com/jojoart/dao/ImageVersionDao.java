package com.jojoart.dao;

import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public interface ImageVersionDao extends GenericDao<ImageVersion, Long>{

    List<ImageVersion> getAllImageVersions(Image image);
    ImageVersion findByTypeAndImage(ImageType imageType, Image image);

}
