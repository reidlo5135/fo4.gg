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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            if(result == null) {
                CommonResult failResult = responseService.getFailResult(-1, "구단주 정보가 존재하지 않습니다.");
                loggingService.commonResultLogging(className, "requestUserInfoByNickName", failResult);
                ett = new ResponseEntity<>(failResult, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                SingleResult<String> singleResult = responseService.getSingleResult(result);
                loggingService.singleResultLogging(className, "requestUserInfoByNickName", singleResult);
                ett = new ResponseEntity<>(singleResult, httpHeaders, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoSVC requestUserInfo Error Occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoSVC requestUserInfo ett : " + ett);
            return ett;
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> requestUserMaxDivisionByAccessId(String accessId) {
        ResponseEntity<?> ett = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            List<Map<String, Object>> resultJSON = restFactoryService.requestUserMaxDivisionByAccessId(accessId);
            Map<String, String> result = new HashMap<>();

            for(int i=0;i<resultJSON.size();i++) {
                log.info("UserInfoSVC requestUserMaxDivisionByAccessId resultJSON.get : " + resultJSON.get(i));

                int matchType = (int)resultJSON.get(i).get("matchType");
                log.info("UserInfoSVC requestUserMaxDivisionByAccessId resultJSON matchType : " + matchType);

                String division = resultJSON.get(i).get("division").toString();
                String achievementDate = resultJSON.get(i).get("achievementDate").toString().split("T")[0];
                log.info("UserInfoSVC requestUserMaxDivisionByAccessId division : " + division);
                log.info("UserInfoSVC requestUserMaxDivisionByAccessId achievementDate : " + achievementDate);

                switch (matchType){
                    case 50:
                        result.put("pvpDivision", division);
                        result.put("pvpDate", achievementDate);
                        break;
                    case 52:
                        result.put("coachDivision", division);
                        result.put("coachDate", achievementDate);
                        break;
                    default:
                        result.put("pvpDivision", null);
                        result.put("pvpDate", null);
                        result.put("coachDivision", null);
                        result.put("coachDate", null);
                        break;
                }
            }

            if(result.size() == 0) {
                result.put("pvpDivision", null);
                result.put("pvpDate", null);
                result.put("coachDivision", null);
                result.put("coachDate", null);
            }

            log.info("UserInfoSVC requestUserMaxDivisionByAccessId result : " + result);

            if(result.isEmpty()) {
                CommonResult failResult = responseService.getFailResult(-1, "requestUserMaxDivisionByAccessId Error Occurred");
                loggingService.commonResultLogging(className, "requestUserMaxDivisionByAccessId", failResult);
                ett = new ResponseEntity<>(failResult, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                SingleResult<Map<String, String>> singleResult = responseService.getSingleResult(result);
                loggingService.singleResultLogging(className, "requestUserMaxDivisionByAccessId", singleResult);
                ett = new ResponseEntity<>(singleResult, httpHeaders, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoSVC requestUserMaxDivisionByAccessId Error Occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoSVC requestUserMaxDivisionByAccessId ett : " + ett);
            return ett;
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> requestDivisionJSON(int pvp, int coach) {
        ResponseEntity<?> ett = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        log.info("UserInfoSVC requestDivisionJSON pvp : " + pvp);
        log.info("UserInfoSVC requestDivisionJSON coach : " + coach);
        try {
            List<Map<String, Object>> resultJSON = restFactoryService.requestDivisionJSON();
            Map<String, String> result = new HashMap<>();

            for(int i=0;i<resultJSON.size();i++) {
                if(pvp == 0) {
                    result.put("pvpDivisionName", "기록이 존재하지 않습니다.");
                } else if(pvp == (int)resultJSON.get(i).get("divisionId")) {
                    String pvpDivisionName = resultJSON.get(i).get("divisionName").toString();
                    log.info("UserInfoSVC requestDivisionJSON pvpDivisionName : " + pvpDivisionName);
                    result.put("pvpDivisionName", pvpDivisionName);
                }
                if(coach == 0) {
                    result.put("coachDivisionName", "기록이 존재하지 않습니다.");
                } else if(coach == (int)resultJSON.get(i).get("divisionId")) {
                    String coachDivisionName = resultJSON.get(i).get("divisionName").toString();
                    log.info("UserInfoSVC requestDivisionJSON coachDivisionName : " + coachDivisionName);
                    result.put("coachDivisionName", coachDivisionName);
                }
            }
            log.info("UserInfoSVC requestDivisionJSON result : " + result);

            if(result.isEmpty()) {
                CommonResult failResult = responseService.getFailResult(-1, "requestDivisionJSON Error Occurred");
                loggingService.commonResultLogging(className, "requestDivisionJSON", failResult);
                ett = new ResponseEntity<>(failResult, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                SingleResult<Map<String, String>> singleResult = responseService.getSingleResult(result);
                loggingService.singleResultLogging(className, "requestDivisionJSON", singleResult);
                ett = new ResponseEntity<>(singleResult, httpHeaders, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserInfoSVC requestDivisionJSON Error Occurred : " + e.getMessage());
        } finally {
            log.info("UserInfoSVC requestDivisionJSON ett : " + ett);
            return ett;
        }
    }
}
