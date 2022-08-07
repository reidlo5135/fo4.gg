package com.reidlo.fo4.main.utils;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestFactoryService {
    private final Logger log = Logger.getLogger(getClass());
    private static final String API_KEY = RestFactory.API_KEY;
    private static final String UserInfoUrl = RestFactory.UserInfoUrl;
    private static final String UserMaxDivisionUrl = RestFactory.UserMaxDivisionUrl;
    private static final String DivisionJSONUrl = RestFactory.DivisionJSONUrl;
    private static String DefaultDivisionImageUrl = RestFactory.DefaultDivisionImageUrl;

    public String requestUserInfoByNickName(String nickname) {
        String result = null;
        try {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", API_KEY);

            RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            result = restTemplate.exchange(UserInfoUrl, HttpMethod.GET, entity, String.class, nickname).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RestFactorySVC requestUserInfo Error Occurred : " + e.getMessage());
        } finally {
            log.info("RestFactorySVC requestUserInfo result : " + result);
            return result;
        }
    }

    public List<Map<String, Object>> requestUserMaxDivisionByAccessId(String accessId) {
        List<Map<String, Object>> result = null;
        try {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", API_KEY);

            RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            result = restTemplate.exchange(UserMaxDivisionUrl, HttpMethod.GET, entity, List.class, accessId).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RestFactorySVC requestUserMaxDivisionByAccessId Error Occurred : " + e.getMessage());
        } finally {
            log.info("RestFactorySVC requestUserMaxDivisionByAccessId result : " + result);
            return result;
        }
    }

    public List<Map<String, Object>> requestDivisionJSON() {
        List<Map<String, Object>> result = null;
        try {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", API_KEY);

            RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            result = restTemplate.exchange(DivisionJSONUrl, HttpMethod.GET, entity, List.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RestFactorySVC requestDivisionJSON Error Occurred : " + e.getMessage());
        } finally {
            log.info("RestFactorySVC requestDivisionJSON result : " + result);
            return result;
        }
    }

    public String setDivisionImageUrl(String division) {
        String DivisionImageUrl = null;
        switch (division) {
            case "월드클래스1":
                DivisionImageUrl = DefaultDivisionImageUrl + 6 + ".png";
                break;
            case "월드클래스2":
                DivisionImageUrl = DefaultDivisionImageUrl + 7 + ".png";
                break;
            case "월드클래스3":
                DivisionImageUrl = DefaultDivisionImageUrl + 8 + ".png";
                break;
            case "프로1":
                DivisionImageUrl = DefaultDivisionImageUrl + 9 + ".png";
                break;
            case "프로2":
                DivisionImageUrl = DefaultDivisionImageUrl + 10 + ".png";
                break;
            case "프로3":
                DivisionImageUrl = DefaultDivisionImageUrl + 11 + ".png";
                break;
            case "세미프로1":
                DivisionImageUrl = DefaultDivisionImageUrl + 12 + ".png";
                break;
            case "세미프로2":
                DivisionImageUrl = DefaultDivisionImageUrl + 13 + ".png";
                break;
            case "세미프로3":
                DivisionImageUrl = DefaultDivisionImageUrl + 14 + ".png";
                break;
            case "기록이 존재하지 않습니다.":
                DivisionImageUrl = null;
                break;
        }
        return DivisionImageUrl;
    }
}