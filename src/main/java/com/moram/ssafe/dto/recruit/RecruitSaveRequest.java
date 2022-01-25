package com.moram.ssafe.dto.recruit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moram.ssafe.domain.company.Company;
import com.moram.ssafe.domain.recruit.Recruit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecruitSaveRequest {

    @NotNull(message = "company id 가 없습니다")
    private Long companyId;

    @NotBlank(message = "title 이 없습니다")
    @Size(max = 45, message = "1이상 45이하여야 합니다.")
    private String title;

    @NotBlank(message = "recruit Url 이 없습니다.")
    @Size(max = 255, message = "255이하여야 합니다.")
    private String recruitUrl;

    @NotBlank(message = "job 이 없습니다")
    @Size(max = 45, message = "1이상 45이하여야 합니다.")
    private String job;

    @NotBlank(message = "emp type 이 없습니다")
    @Size(max = 45, message = "1이상 45이하여야 합니다.")
    private String empType;

    @NotBlank(message = "career 가 없습니다")
    @Size(max = 45, message = "1이상 45이하여야 합니다.")
    private String career;

    @NotBlank(message = "location 이 없습니다")
    @Size(max = 45, message = "1이상 45이하여야 합니다.")
    private String location;

    @NotBlank(message = "tech stack 이 없습니다")
    @Size(max = 255, message = "1이상 255이하여야 합니다.")
    private String techStack;

    @NotNull(message = "open Date 이 없습니다")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime openDate;

    @NotNull(message = "close Date 이 없습니다")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime closeDate;

    @NotNull(message = "sBenefit 이 없습니다")
    private boolean sBenefit;

    @NotBlank(message = "content 가 없습니다")
    private String content;

    @Builder
    public RecruitSaveRequest(Long companyId, String title, String recruitUrl, String job, String empType, String career,
                              String location, String techStack, LocalDateTime openDate, LocalDateTime closeDate,
                              boolean sBenefit, String content) {
        this.companyId = companyId;
        this.title = title;
        this.recruitUrl = recruitUrl;
        this.job = job;
        this.empType = empType;
        this.career = career;
        this.location = location;
        this.techStack = techStack;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.sBenefit = sBenefit;
        this.content = content;
    }

    public Recruit toRecruit(Company company) {
        return Recruit.builder()
                .company(company)
                .title(title)
                .recruitUrl(recruitUrl)
                .job(job)
                .empType(empType)
                .career(career)
                .location(location)
                .techStack(techStack)
                .openDate(openDate)
                .closeDate(closeDate)
                .sBenefit(sBenefit)
                .content(content)
                .build();
    }
}
