package com.moram.ssafe.dto.study;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class StudyUpdateRequest {

    @NotBlank(message = "회사 이름이 존재하지 않습니다.")
    private String company_name;

    @NotBlank(message = "제목이 존재하지 않습니다.")
    private String title;

    @NotBlank(message = "스터디 타입이 존재하지 않습니다.")
    private String study_type;

    @NotBlank(message = "기술 스택이 존재하지 않습니다.")
    private String tech_stack;

    @Min(value=0) @Max(value=1)
    private Integer recruitment;

    @NotBlank(message = "장소 정보가 존재하지 않습니다.")
    private String location;

    @Min(value=0) @Max(value=2)
    private Integer on_off;

    @NotBlank(message = "내용이 존재하지 않습니다.")
    private String content;
}
