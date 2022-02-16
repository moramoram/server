package com.moram.ssafe.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CompanyCommentUpdateRequest {

    @NotNull(message = "commentId 는 null을 허용하지 않습니다.")
    private Long commentId;

    @NotBlank(message = "빈문자열은 허용하지 않습니다.")
    @Length(max = 500,message = "200자 이하로 작성해주세요")
    private String content;
}
