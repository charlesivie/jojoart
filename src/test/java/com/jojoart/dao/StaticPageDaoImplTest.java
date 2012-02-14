package com.jojoart.dao;

import com.jojoart.domain.StaticPage;
import org.hibernate.NonUniqueObjectException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 12/02/2012
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class StaticPageDaoImplTest {

    @Autowired
    private StaticPageDao staticPageDao;
    private StaticPage staticPageSmall;

    @Before
    public void setup() {
        staticPageSmall = new StaticPage("about", "<h1>A Heading</h1><p>A paragraph.</p>", true);
    }

    @Test
    @Transactional
    public void insert_should_insert() {
        staticPageDao.create(staticPageSmall);
        assertEquals(staticPageDao.read(StaticPage.class, "about"), staticPageSmall);
    }

    @Test(expected = PersistenceException.class)
    @Transactional
    public void insert_should_throw_when_duplicate_id() {
        staticPageDao.create(staticPageSmall);
        staticPageDao.create(new StaticPage("about", "<h1>A Heading</h1><p>A paragraph.</p>", true));
    }
    
    @Test
    @Transactional
    public void update_should_update(){
        StaticPage original = staticPageDao.create(staticPageSmall);

        StaticPage expected = new StaticPage("about", "new", false);

        original.setActive(false);
        original.setHtmlContent("new");

        staticPageDao.update(original);

        StaticPage actual = staticPageDao.read(StaticPage.class, "about");

        assertEquals(expected, actual);
    }

    @Test
    @Ignore
    public void delete_should_delete(){
        fail();
    }

    @Test
    @Ignore
    public void insert_should_insert_large_html(){
        fail();
    }


}
