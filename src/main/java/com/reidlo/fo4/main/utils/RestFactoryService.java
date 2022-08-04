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
}