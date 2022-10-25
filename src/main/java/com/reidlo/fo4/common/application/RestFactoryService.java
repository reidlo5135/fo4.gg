package com.reidlo.fo4.common.application;

import com.reidlo.fo4.common.infra.RestFactory;
import lombok.RequiredArgsConstructor;
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
    private static final String API_KEY = RestFactory.API_KEY;
    private static final String USER_INFO_URL = RestFactory.USER_INFO_URL;
    private static final String USER_MAX_DIVISION_URL = RestFactory.USER_MAX_DIVISION_URL;
    private static final String DIVISION_JSON_URL = RestFactory.DIVISION_JSON_URL;
    private static final String DEFAULT_DIVISION_IMAGE_URL = RestFactory.DEFAULT_DIVISION_IMAGE_URL;

    public String requestUserInfoByNickName(String nickname) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(USER_INFO_URL, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class, nickname).getBody();
    }

    public List<Map<String, Object>> requestUserMaxDivisionByAccessId(String accessId) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(USER_MAX_DIVISION_URL, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class, accessId).getBody();
    }

    public List<Map<String, Object>> requestDivisionJSON() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(DIVISION_JSON_URL, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class).getBody();
    }

    public String setDivisionImageUrl(String division) {
        String DivisionImageUrl = null;
        switch (division) {
            case "월드클래스1":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 6 + ".png";
                break;
            case "월드클래스2":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 7 + ".png";
                break;
            case "월드클래스3":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 8 + ".png";
                break;
            case "프로1":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 9 + ".png";
                break;
            case "프로2":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 10 + ".png";
                break;
            case "프로3":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 11 + ".png";
                break;
            case "세미프로1":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 12 + ".png";
                break;
            case "세미프로2":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 13 + ".png";
                break;
            case "세미프로3":
                DivisionImageUrl = DEFAULT_DIVISION_IMAGE_URL + 14 + ".png";
                break;
            case "기록이 존재하지 않습니다.":
                break;
        }
        return DivisionImageUrl;
    }
}