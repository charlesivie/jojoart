package com.jojoart.dao;

import com.jojoart.domain.StaticPage;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 12/02/2012
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public interface StaticPageDao extends GenericDao<StaticPage, Long>  {
    StaticPage findActiveByPath(String path);
    List<StaticPage> listActive();
}
