package com.sopt.Server.controller.request;

import java.util.List;

public record AnswerListRequest(
        String nickname,
        List<AnswerRequest> results) {

    public static AnswerListRequest of(String nickname, List<AnswerRequest> results) {
        return new AnswerListRequest(nickname, results);
    }
}
