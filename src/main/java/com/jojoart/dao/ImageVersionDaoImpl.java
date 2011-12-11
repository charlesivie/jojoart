package com.jojoart.dao;

import com.jojoart.domain.Image;
import com.jojoart.domain.ImageVersion;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ImageVersionDaoImpl extends GenericDaoImpl<ImageVersion, Long> implements ImageVersionDao {

    public List<ImageVersion> getAllImageVersions(Image image) {

        return getEntityManager()
                .createQuery("SELECT a FROM ImageVersion a WHERE :image = a.image", ImageVersion.class)
                .setParameter("image", image)
                .getResultList();

    }

}
