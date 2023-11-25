package com.sopt.Server.controller.request;

import java.util.List;

public record AnswerListResponseDTO(String nickname, List<AnswerResposneDTO> results) {
}
