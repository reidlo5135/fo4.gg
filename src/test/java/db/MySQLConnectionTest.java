package db;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class MySQLConnectionTest {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try(Connection connection = dataSource.getConnection()) {
            log.info("MySQLConnectionTest connection : " + connection);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MySQLConnectionTest Error Occurred : " + e.getMessage());
        }
    }
}
