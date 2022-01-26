package com.moram.ssafe.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CompanyCommentUpdateRequest {

    @NotNull(message = "commentId 는 null을 허용하지 않습니다.")
    private Long commentId;

    @NotBlank(message = "빈문자열은 허용하지 않습니다.")
    @Size(max = 200,message = "200자 이하로 작성해주세요")
    private String content;
}
