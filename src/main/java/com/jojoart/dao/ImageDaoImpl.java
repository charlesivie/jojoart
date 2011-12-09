package com.jojoart.dao;

import com.jojoart.domain.Image;
import com.jojoart.domain.ImageGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ImageDaoImpl extends GenericDaoImpl<Image, Long> implements ImageDao {

    public List<Image> getAllImagesForGroup(ImageGroup imageGroup) {

        return getEntityManager()
                .createQuery("SELECT a FROM Image a WHERE :imageGroup = a.imageGroup", Image.class)
                .setParameter("imageGroup", imageGroup)
                .getResultList();

    }

}
