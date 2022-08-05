package db.dao;

import com.reidlo.fo4.userInfo.model.User;
import com.reidlo.fo4.userInfo.model.UserDAO;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class UserInfoDAOTest {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserDAO userDAO;

    @Test
    public void findAllTest() {
        List<User> list = userDAO.findAll();
        log.info("UserInfoDAOTest findAll list : " + list);
    }
}
