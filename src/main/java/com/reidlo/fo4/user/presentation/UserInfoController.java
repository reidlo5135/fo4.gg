package com.reidlo.fo4.user.presentation;

import com.reidlo.fo4.common.domain.SingleResult;
import com.reidlo.fo4.user.application.UserInfoService;
import com.reidlo.fo4.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/user")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping(value = "/info/{nickname}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SingleResult<String>> getUserInfoByNickName(@PathVariable String nickname) {
        return ResponseEntity.ok().body(userInfoService.requestUserInfoByNickName(nickname));
    }

    @GetMapping(value = "/division/{accessId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getUserMaxDivisionByAccessId(@PathVariable String accessId) {
        return ResponseEntity.ok().body(userInfoService.requestUserMaxDivisionByAccessId(accessId));
    }

    @GetMapping(value = "/division/json/{pvp}/{coach}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getDivisionJSON(@PathVariable int pvp, @PathVariable int coach) {
        return ResponseEntity.ok().body(userInfoService.requestDivisionJSON(pvp, coach));
    }

    @PostMapping(value = "/register/userInfo", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> registerUserInfo(@RequestBody User user) throws SQLException {
        return ResponseEntity.ok().body(userInfoService.registerUserInfo(user));
    }
}
