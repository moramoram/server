package com.moram.ssafe.dto.study;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyScrap;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudyResponse {

    private Long studyId;

    private UserResponse writerInfo;

    private List<StudyCommentResponse> comments = new ArrayList<>();

    private String company_name;

    private String title;

    private String studyType;

    private String techStack;

    private Boolean recruitment;

    private String memberNumber;

    private String thumbnailImg;

    private Integer onOff;

    private String content;

    private Integer views;

    private Boolean scrapStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    private StudyResponse(Long studyId, UserResponse writerInfo, String title,
                          String techStack, Boolean recruitment, String memberNumber, String thumbnailImg, String studyType, Integer onOff) {
        this.studyId = studyId;
        this.writerInfo = writerInfo;
        this.title = title;
        this.techStack = techStack;
        this.recruitment = recruitment;
        this.studyType = studyType;
        this.thumbnailImg = thumbnailImg;
        this.onOff = onOff;
    }

    public static StudyResponse from(Study study) { //전체 조회
        return StudyResponse.builder().studyId(study.getId()).title(study.getTitle())
                .writerInfo(UserResponse.from(study.getUser())).recruitment(study.getRecruitment())
                .techStack(study.getTechStack()).studyType(study.getStudyType())
                .thumbnailImg(study.getThumbnailImg())
                .onOff(study.getOnOff()).build();
    }

    public static StudyResponse from(StudyScrap scrap) { //전체 조회
        Study study = scrap.getStudy();

        Long studyId = study.getId();
        UserResponse writerInfo = UserResponse.from(study.getUser());
        String title = study.getTitle();
        String techStack = study.getTechStack();
        Boolean recruitment = study.getRecruitment();
        String thumbnailImg = study.getThumbnailImg();
        String studyType = study.getStudyType();
        Integer onOff = study.getOnOff();

        return StudyResponse.builder().studyId(studyId).title(title)
                .writerInfo(writerInfo).recruitment(recruitment)
                .techStack(techStack).studyType(studyType).thumbnailImg(thumbnailImg).onOff(onOff).build();
    }

    public StudyResponse(Study study, Boolean scrapStatus) { //단건 조회
        this.studyId = study.getId();
        this.writerInfo = UserResponse.from(study.getUser());
        this.comments = study.getCommentList().stream()
                .map(comment -> StudyCommentResponse.from(comment)).collect(Collectors.toList());
        this.company_name = study.getCompanyName();
        this.title = study.getTitle();
        this.studyType = study.getStudyType();
        this.techStack = study.getTechStack();
        this.recruitment = study.getRecruitment();
        this.memberNumber = study.getMemberNumber();
        this.thumbnailImg = study.getThumbnailImg();
        this.onOff = study.getOnOff();
        this.content = study.getContent();
        this.views = study.getViews();
        this.scrapStatus = scrapStatus;
        this.createdDate = study.getCreatedDate();
        this.modifiedDate = study.getModifiedDate();
    }

}
