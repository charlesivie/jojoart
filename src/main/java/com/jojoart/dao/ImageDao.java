package com.jojoart.dao;

import com.jojoart.domain.Image;
import com.jojoart.domain.ImageGroup;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public interface ImageDao extends GenericDao<Image, Long>{

    List<Image> getAllImagesForGroup(ImageGroup imageGroup);

}
