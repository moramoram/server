package com.moram.ssafe.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CompanyCommentRequest {

    @NotNull(message = "companyId 는 null을 허용하지 않습니다.")
    private Long companyId;

    @NotBlank(message = "빈문자열은 허용하지 않습니다.")
    @Length(max = 500, message = "500자 이하로 작성해주세요")
    private String content;

}
