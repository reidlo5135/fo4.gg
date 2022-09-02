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
    private static final String UserInfoUrl = RestFactory.UserInfoUrl;
    private static final String UserMaxDivisionUrl = RestFactory.UserMaxDivisionUrl;
    private static final String DivisionJSONUrl = RestFactory.DivisionJSONUrl;
    private static String DefaultDivisionImageUrl = RestFactory.DefaultDivisionImageUrl;

    public String requestUserInfoByNickName(String nickname) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(UserInfoUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class, nickname).getBody();
    }

    public List<Map<String, Object>> requestUserMaxDivisionByAccessId(String accessId) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(UserMaxDivisionUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class, accessId).getBody();
    }

    public List<Map<String, Object>> requestDivisionJSON() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(DivisionJSONUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class).getBody();
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
                break;
        }
        return DivisionImageUrl;
    }
}