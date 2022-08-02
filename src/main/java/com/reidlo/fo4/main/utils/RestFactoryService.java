package com.reidlo.fo4.main.utils;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestFactoryService {
    private final Logger log = Logger.getLogger(getClass());
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjI2ODk1NjQ1OSIsImF1dGhfaWQiOiIyIiwiZXhwIjoxNjc0NjE4NDc1LCJpYXQiOjE2NTkwNjY0NzUsIm5iZiI6MTY1OTA2NjQ3NSwic2VydmljZV9pZCI6IjQzMDAxMTQ4MSIsInRva2VuX3R5cGUiOiJBY2Nlc3NUb2tlbiJ9.J6VCUjKmQwegb5NklulF2yVvhF8xtW374FVbkQfLtME";
    private final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
    private final String UserMaxDivisionUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/maxdivision";

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

    public String requestUserMaxDivisionByAccessId(String accessId) {
        String result = null;
        try {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", API_KEY);

            RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            result = restTemplate.exchange(UserMaxDivisionUrl, HttpMethod.GET, entity, String.class, accessId).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RestFactorySVC requestUserMaxDivisionByAccessId Error Occurred : " + e.getMessage());
        } finally {
            log.info("RestFactorySVC requestUserMaxDivisionByAccessId result : " + result);
            return result;
        }
    }
}
