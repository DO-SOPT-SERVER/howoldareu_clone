package com.sopt.Server.controller.request;

public record AnswerRequest(Long questionId, boolean answerType) {

    public static AnswerRequest of(
            Long questionId,
            boolean answerType
    ) {
        return new AnswerRequest(
                questionId,
                answerType
        );
    }
}
