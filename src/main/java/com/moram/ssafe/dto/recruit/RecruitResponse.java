package com.moram.ssafe.dto.recruit;

import com.moram.ssafe.domain.recruit.Recruit;
import com.moram.ssafe.dto.company.CompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitResponse {

    private Long recruitId;

    private CompanyResponse company;

    private String title;

    private String recruitUrl;

    private String job;

    private String empType;

    private String career;

    private String location;

    private String techStack;

    private LocalDateTime openDate;

    private LocalDateTime closeDate;

    private boolean sBenefit;

    private String content;

    private Integer views;

    private boolean likeStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    public static RecruitResponse of(Recruit recruit) {
        return RecruitResponse.builder()
                .recruitId(recruit.getId())
                .company(CompanyResponse.of(recruit.getCompany()))
                .title(recruit.getTitle())
                .recruitUrl(recruit.getRecruitUrl())
                .job(recruit.getJob())
                .empType(recruit.getEmpType())
                .career(recruit.getCareer())
                .location(recruit.getLocation())
                .techStack(recruit.getTechStack())
                .openDate(recruit.getOpenDate())
                .closeDate(recruit.getCloseDate())
                .sBenefit(recruit.isSBenefit())
                .content(recruit.getContent())
                .views(recruit.getViews())
                .likeStatus(false)
                .createdDate(recruit.getCreatedDate())
                .modifiedDate(recruit.getModifiedDate())
                .build();
    }
}
