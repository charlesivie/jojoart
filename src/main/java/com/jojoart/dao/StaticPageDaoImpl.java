package com.jojoart.dao;

import com.jojoart.domain.StaticPage;
import org.springframework.stereotype.Repository;

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
public class StaticPageDaoImpl extends GenericDaoImpl<StaticPage, String> implements StaticPageDao {
}
