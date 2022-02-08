package com.moram.ssafe.dto.study;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudySearch {

    private String title;

    private String studyType;

    private String criteria;

    public static StudySearch of(String title, String studyType, String criteria) {
        return new StudySearch(title, studyType, criteria);
    }
}
