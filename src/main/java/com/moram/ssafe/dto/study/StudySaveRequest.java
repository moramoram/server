package com.moram.ssafe.dto.study;

import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class StudySaveRequest {

    @NotBlank(message = "회사 이름이 없습니다.")
    private String company_name;

    @NotBlank(message = "제목이 없습니다.")
    private String title;

    @NotBlank(message = "스터디 유형이 없습니다.")
    private String study_type;

    @NotBlank(message = "기술 스택이 없습니다.")
    private String tech_stack;

    @Min(value=0) @Max(value=1)
    private Integer recruitment;

    @NotBlank(message = "장소가 없습니다.")
    private String location;

    @Min(value=0) @Max(value=2)
    private Integer on_off;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public Study of(User user){
        return Study.builder().user(user).company_name(company_name).title(title).study_type(study_type)
                .tech_stack(tech_stack).recruitment(recruitment).location(location)
                .on_off(on_off).content(content).build();
    }
}
