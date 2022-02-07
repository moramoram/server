package com.moram.ssafe.dto.study;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class StudyUpdateRequest {

    private String companyName;

    @NotBlank(message = "제목이 존재하지 않습니다.")
    @Size(max = 45, message = "1자 이상 45자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "스터디 타입이 존재하지 않습니다.")
    private String studyType;

    @Size(max = 255, message = "255자 이하여야 합니다.")
    private String techStack;

    @Min(value=0) @Max(value=1)
    private Integer recruitment;

    @NotBlank(message = "장소 정보가 존재하지 않습니다.")
    private String location;

    @Min(value=0) @Max(value=2)
    private Integer onOff;

    @NotBlank(message = "내용이 존재하지 않습니다.")
    private String content;
}
