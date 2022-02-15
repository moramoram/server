package com.moram.ssafe.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardCommentUpdateRequest {

    @NotBlank(message = "댓글의 내용이 없습니다.")
    @Length(max = 500, message = "500자 이하여야합니다.")
    private String content;

}
