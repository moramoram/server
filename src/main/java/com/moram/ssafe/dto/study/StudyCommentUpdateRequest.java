package com.moram.ssafe.dto.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class StudyCommentUpdateRequest {

    @NotBlank(message = "빈문자열은 허용하지 않습니다.")
    @Length(max = 500, message = "500자 이하로 작성해주세요")
    private String content;

}
