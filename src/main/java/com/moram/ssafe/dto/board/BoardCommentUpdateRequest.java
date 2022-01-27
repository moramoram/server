package com.moram.ssafe.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardCommentUpdateRequest {

    @NotBlank(message = "댓글의 내용이 없습니다.")
    private String content;

}
