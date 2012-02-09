package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.Image;
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
    public List<Image> listImagesByCategory(Category category) {
        return getEntityManager()
                .createQuery("SELECT a FROM Image a WHERE :category = a.category", Image.class)
                .setParameter("category", category)
                .getResultList();

    }

    public List<Image> listActiveImagesByCategory(Category category) {
        return getEntityManager()
                .createQuery("SELECT a FROM Image a WHERE :category = a.category AND a.active = true", Image.class)
                .setParameter("category", category)
                .getResultList();
    }
}
