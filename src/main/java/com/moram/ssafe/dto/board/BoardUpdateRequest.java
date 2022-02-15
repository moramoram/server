package com.moram.ssafe.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {

    @NotBlank(message = "제목이 없습니다.")
    @Length(max = 45, message = "1자 이상 45자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public static BoardUpdateRequest from(String title, String content){
        return new BoardUpdateRequest(title, content);
    }
}
