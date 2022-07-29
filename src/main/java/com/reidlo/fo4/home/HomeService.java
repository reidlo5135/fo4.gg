package com.reidlo.fo4.home;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final Logger log = Logger.getLogger(getClass());
    private final RestFactory restFactory;

    public String requestUserInfo(String nickname) {
        log.info("HomeSVC nickname : " + nickname);
        return restFactory.requestUserInfo(nickname);
    }
}
