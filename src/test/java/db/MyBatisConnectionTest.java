package db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class MyBatisConnectionTest {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testFactory() {
        log.info("MyBatisConnectionTest sqlSessionFactory : " + sqlSessionFactory);
    }

    @Test
    public void testSession() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            log.info("MyBatisConnectionTest sqlSession : " + sqlSession);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MyBatisConnectionTest testSession Error Occurred : " + e.getMessage());
        }
    }
}
