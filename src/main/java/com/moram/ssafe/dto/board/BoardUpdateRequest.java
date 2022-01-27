package com.moram.ssafe.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BoardUpdateRequest {

    @NotBlank(message = "제목이 없습니다.")
    private String title;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public static BoardUpdateRequest from(String title, String content){
        return new BoardUpdateRequest(title, content);
    }
}
