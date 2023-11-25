package com.sopt.Server.controller.response;

public record ResultResponseDTO(String nickname, int resultAge, String title, String content, String imgUrl) {
    public static ResultResponseDTO of(String nickname, int resultAge, String title, String content, String imgUrl) {
        return new ResultResponseDTO(nickname, resultAge, title, content, imgUrl);
    }
}
