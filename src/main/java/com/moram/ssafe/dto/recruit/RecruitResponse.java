package com.moram.ssafe.dto.recruit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moram.ssafe.domain.recruit.Recruit;
import com.moram.ssafe.dto.company.CompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private boolean scrapStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    public static RecruitResponse from(Recruit recruit) {
        return RecruitResponse.builder()
                .recruitId(recruit.getId())
                .company(CompanyResponse.from(recruit.getCompany()))
                .title(recruit.getTitle())
                .job(recruit.getJob())
                .openDate(recruit.getOpenDate())
                .closeDate(recruit.getCloseDate())
                .sBenefit(recruit.isSBenefit())
                .techStack(recruit.getTechStack())
                .scrapStatus(false)
                .build();
    }

    public static RecruitResponse from(Recruit recruit, boolean scrapStatus) {
        return new RecruitResponse(
                recruit.getId(), CompanyResponse.from(recruit.getCompany()), recruit.getTitle(),
                recruit.getRecruitUrl(), recruit.getJob(), recruit.getEmpType(), recruit.getCareer(),
                recruit.getLocation(), recruit.getTechStack(), recruit.getOpenDate(), recruit.getCloseDate(),
                recruit.isSBenefit(), recruit.getContent(), recruit.getViews(), scrapStatus,
                recruit.getCreatedDate(), recruit.getModifiedDate()
        );
    }
}
