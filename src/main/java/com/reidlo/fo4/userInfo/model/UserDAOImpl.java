package com.reidlo.fo4.userInfo.model;

import com.reidlo.fo4.userInfo.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO{
    private final Logger log = Logger.getLogger(getClass());
    private final UserInfoMapper userInfoMapper;

    @Override
    public List<User> findAll() {
        List<User> list = userInfoMapper.findAll();
        log.info("UserDAOImpl findAll list : " + list);
        return list;
    }
}
