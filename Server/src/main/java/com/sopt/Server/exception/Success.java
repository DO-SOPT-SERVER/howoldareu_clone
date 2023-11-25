package com.sopt.Server.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    // 200 OK
    GET_HEALTH_CHECK_SUCCESS(HttpStatus.OK, "서버 상태 체크에 성공했습니다"),
    GET_QUESTION_LIST_SUCCESS(HttpStatus.OK, "질문 리스트 조회 성공");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatus(){
        return httpStatus.value();
    }

    public String getMessage(){
        return message;
    }
}
