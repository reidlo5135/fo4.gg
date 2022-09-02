package com.reidlo.fo4.common.presentation;

import com.reidlo.fo4.user.application.UserInfoService;
import com.reidlo.fo4.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FrontController {
    private final Logger log = Logger.getLogger(getClass());

    private final UserInfoService userInfoService;

    @GetMapping(value = "/")
    public String home() {
        log.info("MainController homepage");
        return "index";
    }

    @GetMapping(value = "/details/pvp/{nickname}")
    public ModelAndView pvpDetails(ModelAndView modelAndView, @PathVariable String nickname) {
        log.info("MainController details pvp nickname : " + nickname);

        List<User> userList = userInfoService.findUserListByNickName(nickname);
        log.info("MainController details pvp userList : " + userList);

        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("/match/pvpDetails");

        return modelAndView;
    }

    @GetMapping(value = "/details/coach/{nickname}")
    public ModelAndView coachDetails(ModelAndView modelAndView, @PathVariable String nickname) {
        log.info("MainController details coach nickname : " + nickname);

        List<User> userList = userInfoService.findUserListByNickName(nickname);
        log.info("MainController details coach userList : " + userList);

        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("/match/coachDetails");

        return modelAndView;
    }
}
