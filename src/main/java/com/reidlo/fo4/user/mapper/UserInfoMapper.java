package com.reidlo.fo4.user.mapper;

import com.reidlo.fo4.user.domain.User;

import java.util.List;

public interface UserInfoMapper {

    List<User> findAll();

    List<User> findByNickName(String nickname);

    int register(User user);
}
