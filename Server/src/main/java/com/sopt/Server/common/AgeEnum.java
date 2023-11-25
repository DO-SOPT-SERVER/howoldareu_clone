package com.sopt.Server.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AgeEnum {
    TEENAGER("제목10", "내용10", "url10"),
    TWENTIES("제목20", "내용20", "url20"),
    THIRTIES("제목30", "내용30", "url30"),
    FORTIES("제목40", "내용40", "url40"),
    FIFTIES("제목50", "내용50", "url50");

    private final String title;
    private final String content;
    private final String imageUrl;

}

