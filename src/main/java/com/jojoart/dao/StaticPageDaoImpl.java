package com.jojoart.dao;

import com.jojoart.domain.StaticPage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.net.URLEncoder.encode;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 12/02/2012
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class StaticPageDaoImpl extends GenericDaoImpl<StaticPage, Long> implements StaticPageDao {

    @Transactional
    public StaticPage create(StaticPage staticPage) {
        try {
            staticPage.setPath(encode(staticPage.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.create(staticPage);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Transactional
    public StaticPage update(StaticPage staticPage) {
        try {
            staticPage.setPath(encode(staticPage.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.update(staticPage);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Transactional
    public StaticPage findActiveByPath(String path) {

        return getEntityManager()
                .createQuery("SELECT a FROM StaticPage a WHERE :path = a.path AND a.active = true", StaticPage.class)
                .setParameter("path", path)
                .getSingleResult();
    }
}
