package com.jojoart.dao;

import com.jojoart.domain.ImageGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Transactional
public class ImageGroupDaoImpl extends GenericDaoImpl<ImageGroup, Long> implements ImageGroupDao {
}
