package com.moram.ssafe.dto.recruit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitSearch {

    @NotNull(message = "null을 허용하지 않습니다.")
    private String title;

    @NotNull(message = "null을 허용하지 않습니다.")
    private List<String> techStack;

    @NotNull(message = "null을 허용하지 않습니다.")
    private String job;

    @NotNull(message = "null을 허용하지 않습니다.")
    private String criteria;
}
