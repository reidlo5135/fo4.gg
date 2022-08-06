package db.dao;

import com.reidlo.fo4.userInfo.model.User;
import com.reidlo.fo4.userInfo.model.UserInfoRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class UserInfoRepoTest {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void findAllTest() throws SQLException {
        List<User> list = userInfoRepository.findAll();
        log.info("UserInfoDAOTest findAll list : " + list);
    }
}
