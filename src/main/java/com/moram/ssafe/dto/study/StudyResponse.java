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

    private Integer totalComment;

    private Integer totalScrap;

    private boolean scrapStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public StudyResponse(Study study, Integer totalComment){ //전체 조회
        this.studyId = study.getId();
        this.writerInfo = UserResponse.from(study.getUser());
        this.company_name = study.getCompany_name();
        this.title = study.getTitle();
        this.study_type = study.getStudy_type();
        this.tech_stack = study.getTech_stack();
        this.recruitment = study.getRecruitment();
        this.location = study.getLocation();
        this.on_off = study.getOn_off();
        this.content = study.getContent();
        this.views = study.getViews();
        this.totalComment = totalComment;
        this.totalScrap = study.getScrapList().size();
        this.createdDate = study.getCreatedDate();
        this.modifiedDate = study.getModifiedDate();
    }

    @Builder
    public StudyResponse(Long studyId, UserResponse writerInfo, String company_name, String title,
                         String study_type, String tech_stack, Integer recruitment, String location, Integer on_off,
                         String content, Integer views, LocalDateTime createdDate, LocalDateTime modifiedDate,
                         Integer totalComment, Integer totalScrap) {
        this.studyId = studyId;
        this.writerInfo = writerInfo;
        this.company_name = company_name;
        this.title = title;
        this.study_type = study_type;
        this.tech_stack = tech_stack;
        this.recruitment = recruitment;
        this.location = location;
        this.on_off = on_off;
        this.content = content;
        this.views = views;
        this.totalComment = totalComment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.totalScrap = totalScrap;
    }


    public static StudyResponse of(StudyScrap scrap){
        Study study = scrap.getStudy();

        Long studyId = study.getId();
        UserResponse writerInfo = UserResponse.from(study.getUser());
        String company_name = study.getCompany_name();
        String title = study.getTitle();
        String study_type = study.getStudy_type();
        String tech_stack = study.getTech_stack();
        Integer recruitment = study.getRecruitment();
        String location = study.getLocation();
        Integer on_off = study.getOn_off();
        String content = study.getContent();
        Integer views = study.getViews();
        Integer totalComment = study.getCommentList().size();
        Integer totalScrap = study.getScrapList().size();
        LocalDateTime createdDate = study.getCreatedDate();
        LocalDateTime modifiedDate = study.getModifiedDate();

        return StudyResponse.builder().studyId(studyId).writerInfo(writerInfo).company_name(company_name)
                .title(title).study_type(study_type).tech_stack(tech_stack).recruitment(recruitment)
                .location(location).on_off(on_off).content(content).views(views).totalComment(totalComment)
                .totalScrap(totalScrap).createdDate(createdDate).modifiedDate(modifiedDate).build();
    }

    public StudyResponse(Study study, boolean scrapStatus){ //단건 조회
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
        this.totalScrap = study.getScrapList().size();
        this.createdDate = study.getCreatedDate();
        this.modifiedDate = study.getModifiedDate();
    }

}
