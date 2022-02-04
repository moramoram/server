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

    private String study_type;

    private String tech_stack;

    private Integer recruitment;

    private String location;

    private Integer on_off;

    private String content;

    private Integer views;

    private Boolean scrapStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    private StudyResponse(Long studyId, UserResponse writerInfo, String title, String tech_stack, Integer recruitment) {
        this.studyId = studyId;
        this.writerInfo = writerInfo;
        this.title = title;
        this.tech_stack = tech_stack;
        this.recruitment = recruitment;
    }

    public static StudyResponse from(Study study) { //전체 조회
        return StudyResponse.builder().studyId(study.getId()).title(study.getTitle())
                .writerInfo(UserResponse.from(study.getUser())).recruitment(study.getRecruitment())
                .tech_stack(study.getTech_stack()).build();
    }


    public static StudyResponse from(StudyScrap scrap){ //전체 조회
       Study study = scrap.getStudy();

        Long studyId = study.getId();
        UserResponse writerInfo = UserResponse.from(study.getUser());
        String title = study.getTitle();
        String tech_stack = study.getTech_stack();
        Integer recruitment = study.getRecruitment();

        return StudyResponse.builder().studyId(studyId).title(title)
                .writerInfo(writerInfo).recruitment(recruitment)
                .tech_stack(tech_stack).build();
    }

    public StudyResponse(Study study, Boolean scrapStatus){ //단건 조회
        this.studyId = study.getId();
        this.writerInfo = UserResponse.from(study.getUser());
        this.comments = study.getCommentList().stream()
                .map(comment -> StudyCommentResponse.from(comment)).collect(Collectors.toList());
        this.company_name = study.getCompany_name();
        this.title = study.getTitle();
        this.study_type = study.getStudy_type();
        this.tech_stack = study.getTech_stack();
        this.recruitment = study.getRecruitment();
        this.location = study.getLocation();
        this.on_off = study.getOn_off();
        this.content = study.getContent();
        this.views = study.getViews();
        this.scrapStatus = scrapStatus;
        this.createdDate = study.getCreatedDate();
        this.modifiedDate = study.getModifiedDate();
    }

}
