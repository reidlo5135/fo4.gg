package com.reidlo.fo4.userInfo.service;

import com.reidlo.fo4.main.response.model.CommonResult;
import com.reidlo.fo4.main.response.model.SingleResult;
import com.reidlo.fo4.main.response.service.ResponseLoggingService;
import com.reidlo.fo4.main.response.service.ResponseService;
import com.reidlo.fo4.main.utils.RestFactoryService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final Logger log = Logger.getLogger(getClass());
    private static final String className = UserInfoService.class.toString();

    private final RestFactoryService restFactoryService;
    private final ResponseService responseService;
    private final ResponseLoggingService loggingService;

    @Transactional(readOnly = true)
    public ResponseEntity<?> requestUserInfoByNickName(String nickname) {
        ResponseEntity<?> ett = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            String result = restFactoryService.requestUserInfoByNickName(nickname);
            log.info("UserInfoSVC requestUserInfo result : " + result);

            if(result != null && !result.equals("")) {
                SingleResult<String> singleResult = responseService.getSingleResult(result);
                loggingService.singleResultLogging(className, "requestUserInfoByNickName", singleResult);
                ett = new ResponseEntity<>(singleResult, httpHeaders, HttpStatus.OK);
            } else {
                CommonResult failResult = responseService.getFailResult(-1, "회원정보가 존재하지 않습니다.");
                loggingService.commonResultLogging(className, "requestUserInfoByNickName", failResult);
                ett = new ResponseEntity<>(failResult, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoSVC requestUserInfo error occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoSVC requestUserInfo ett : " + ett);
            return ett;
        }
    }
}
