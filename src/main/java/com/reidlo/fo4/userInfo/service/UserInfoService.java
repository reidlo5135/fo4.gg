package com.reidlo.fo4.userInfo.service;

import com.reidlo.fo4.main.utils.RestFactory;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final Logger log = Logger.getLogger(getClass());
    private final RestFactory restFactory;

    @Transactional(readOnly = true)
    public String requestUserInfo(String nickname) {
        String result = null;
        try {
            result = restFactory.requestUserInfo(nickname);
            log.info("UserInfoSVC requestUserInfo result : " + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoSVC requestUserInfo error occurred : " + e.getMessage());
        }
        return result;
    }
}
