package com.moram.ssafe.dto.recruit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitSearch {

    private String title;

    private List<String> techStack;

    private String job;
}
