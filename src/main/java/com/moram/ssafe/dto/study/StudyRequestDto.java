package com.moram.ssafe.dto.study;

import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyRequestDto {

    private String companyName;

    private String title;

    private String studyType;

    private String techStack;

    private String memberNumber;

    private String thumbnailImg;

    private Integer onOff;

    private String content;

    @Builder
    public StudyRequestDto(String companyName, String title, String studyType, String techStack,
                           String memberNumber, String thumbnailImg, Integer onOff, String content) {
        this.companyName = companyName;
        this.title = title;
        this.studyType = studyType;
        this.techStack = techStack;
        this.memberNumber = memberNumber;
        this.thumbnailImg = thumbnailImg;
        this.onOff = onOff;
        this.content = content;
    }

    public Study toStudy(User user) {
        return Study.builder()
                .user(user)
                .companyName(companyName)
                .title(title)
                .studyType(studyType)
                .techStack(techStack)
                .memberNumber(memberNumber)
                .thumbnailImg(thumbnailImg)
                .onOff(onOff)
                .content(content)
                .build();
    }
}
