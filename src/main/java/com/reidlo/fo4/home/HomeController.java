package com.reidlo.fo4.home;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Logger log = Logger.getLogger(getClass());
    private final HomeService homeService;

    @GetMapping(value = "/")
    public String home() {
        log.info("HOME!");
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/v1/api/user/{nickname}", produces = "application/json;charset=UTF-8")
    public String get(@PathVariable String nickname) {
        try {
            log.info("NICKNAME : " + nickname);
            return homeService.requestUserInfo(nickname);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
