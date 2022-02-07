package com.moram.ssafe.dto.study;

import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class StudySaveRequest {

    private String companyName;

    @NotBlank(message = "제목이 없습니다.")
    @Size(max = 45, message = "1자 이상 45자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "스터디 유형이 없습니다.")
    private String studyType;

    @Size(max = 255, message = "255자 이하여야 합니다.")
    private String techStack;

    @Min(value=0) @Max(value=1)
    private Integer recruitment;

    @NotBlank(message = "장소가 없습니다.")
    private String location;

    @Min(value=0) @Max(value=2)
    private Integer onOff;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public Study of(User user){
        return Study.builder().user(user).companyName(companyName).title(title).studyType(studyType)
                .techStack(techStack).recruitment(recruitment).location(location)
                .onOff(onOff).content(content).build();
    }
}
