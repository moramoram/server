package com.moram.ssafe.dto.study;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudyFormRequest {

    private String companyName;

    @NotBlank(message = "제목이 없습니다.")
    @Size(max = 45, message = "1자 이상 45자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "스터디 유형이 없습니다.")
    private String studyType;
    
    @Size(max = 255, message = "255자 이하여야 합니다.")
    private String techStack;

    @NotBlank(message = "모집인원이 없습니다.")
    private String memberNumber;

    private MultipartFile thumbnailImg;

    @Min(value = 0)
    @Max(value = 2)
    private Integer onOff;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

}
