package dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-test.xml"})
@Transactional
public abstract class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    DataSource dataSrc;
    @PersistenceContext
    private EntityManager em;

    @Before
    public void beforeTest() {
        setDataSource(dataSrc);
        super.executeSqlScript("classpath:banque-test.sql", false);
    }
    protected void flush() {
        em.flush();
    }
    public DataSource getDataSrc() {
        return dataSrc;
    }
    public void setDataSrc(DataSource dataSrc) {
        this.dataSrc = dataSrc;
    }
}
