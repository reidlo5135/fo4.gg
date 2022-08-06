package com.reidlo.fo4.userInfo.mapper;

import com.reidlo.fo4.userInfo.model.User;

import java.util.List;

public interface UserInfoMapper {

    List<User> findAll();

    List<User> findByNickName(String nickname);

    int register(User user);
}