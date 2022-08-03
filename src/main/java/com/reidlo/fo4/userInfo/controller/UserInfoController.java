package com.reidlo.fo4.userInfo.controller;

import com.reidlo.fo4.main.response.service.ResponseLoggingService;
import com.reidlo.fo4.userInfo.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/user")
public class UserInfoController {
    private final Logger log = Logger.getLogger(getClass());
    private static final String className = UserInfoController.class.toString();

    private final UserInfoService userInfoService;
    private final ResponseLoggingService loggingService;

    @GetMapping(value = "/info/{nickname}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getUserInfoByNickName(@PathVariable String nickname) {
        ResponseEntity<?> ett = null;
        loggingService.httpPathStrLogging(className, "getUserInfoByNickName", nickname, "", "");
        try {
            ett = userInfoService.requestUserInfoByNickName(nickname);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoController requestUserInfo Error Occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoController requestUserInfo ett : " + ett);
            return ett;
        }
    }

    @GetMapping(value = "/division/{accessId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getUserMaxDivisionByAccessId(@PathVariable String accessId) {
        ResponseEntity<?> ett = null;
        loggingService.httpPathStrLogging(className, "getUserMaxDivisionByAccessId", accessId, "", "");
        try {
            ett = userInfoService.requestUserMaxDivisionByAccessId(accessId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoController getUserMaxDivisionByAccessId Error Occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoController getUserMaxDivisionByAccessId ett : " + ett);
            return ett;
        }
    }

    @GetMapping(value = "/division/json/{pvp}/{coach}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getDivisionJSON(@PathVariable int pvp, @PathVariable int coach) {
        ResponseEntity<?> ett = null;
        loggingService.httpPathStrLogging(className, "getDivisionJSON", String.valueOf(pvp), String.valueOf(coach), "");
        try {
            ett = userInfoService.requestDivisionJSON(pvp, coach);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoController getDivisionJSON Error Occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoController getDivisionJSON ett : " + ett);
            return ett;
        }
    }
}
