package com.reidlo.fo4.userInfo.controller;

import com.reidlo.fo4.userInfo.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/user")
public class UserInfoController {
    private final Logger log = Logger.getLogger(getClass());
    private final UserInfoService userInfoService;

    @GetMapping(value = "/{nickname}", produces = "application/json;charset=UTF-8")
    public String get(@PathVariable String nickname) {
        String result = null;
        try {
            result = userInfoService.requestUserInfo(nickname);
            log.info("UserInfoController requestUserInfo result : " + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoController requestUserInfo error occurred : " + e.getMessage());
        }
        return result;
    }
}
