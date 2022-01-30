package com.moram.ssafe.dto.recruit;

import com.moram.ssafe.domain.recruit.Recruit;
import com.moram.ssafe.dto.company.CompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
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

    public static RecruitResponse from(Recruit recruit) {
        return new RecruitResponse(
                recruit.getId(), CompanyResponse.from(recruit.getCompany()), recruit.getTitle(),
                recruit.getRecruitUrl(), recruit.getJob(), recruit.getEmpType(), recruit.getCareer(),
                recruit.getLocation(), recruit.getTechStack(), recruit.getOpenDate(), recruit.getCloseDate(),
                recruit.isSBenefit(), recruit.getContent(), recruit.getViews(), false,
                recruit.getCreatedDate(), recruit.getModifiedDate()
        );
    }
}
