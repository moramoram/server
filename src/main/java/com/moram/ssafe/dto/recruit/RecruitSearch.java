package com.moram.ssafe.dto.recruit;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitSearch {

    private String title;

    private List<String> techStack;

    private String job;

    private String criteria;

    public static RecruitSearch of(String title, List<String> techStack, String job, String criteria) {
        return new RecruitSearch(title, techStack, job, criteria);
    }
}
