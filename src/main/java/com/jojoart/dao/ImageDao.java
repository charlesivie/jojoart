package com.jojoart.dao;

import com.jojoart.domain.Category;
import com.jojoart.domain.Image;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public interface ImageDao extends GenericDao<Image, Long> {
    List<Image> listImagesByCategory(Category category);
}
