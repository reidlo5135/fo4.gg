package com.reidlo.fo4.user.domain;

import com.reidlo.fo4.user.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository {
    private final Logger log = Logger.getLogger(getClass());
    private final UserInfoMapper userInfoMapper;

    public List<User> findAll() throws SQLException {
        List<User> list = userInfoMapper.findAll();
        log.info("UserInfoRepo findAll list : " + list);
        return list;
    }

    public List<User> findByNickName(String nickname) throws SQLException {
        log.info("UserInfoRepo findByNickName nickname : " + nickname);
        List<User> list = userInfoMapper.findByNickName(nickname);
        return list;
    }

    public int register(User user) throws SQLException {
        log.info("UserInfoRepo register user : " + user);
        int result = userInfoMapper.register(user);
        log.info("UserInfoRepo register result : " + result);
        return result;
    }
}
