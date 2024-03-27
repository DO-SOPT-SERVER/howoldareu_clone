package com.sopt.Server.controller.response;

import com.sopt.Server.domain.Question;

public record GetQuestionResponse(
        Long questionId,
        String questionContent
) {
    static public GetQuestionResponse of(
            Question question
    ) {
        return new GetQuestionResponse(
                question.getQuestionId(),
                question.getQuestionContent());
    }
}
