package com.reidlo.fo4.main.response.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(0, "성공"),
    FAIL(-1, "실패");

    private int code;
    private String msg;
}