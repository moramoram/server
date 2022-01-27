package com.moram.ssafe.domain.recruit;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.domain.company.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_recruit")
public class Recruit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", updatable = false)
    private Company company;

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

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<RecruitScrap> recruitScraps = new ArrayList<>();

    @Embedded
    private final RecruitScraps recruitScraps = new RecruitScraps();


    @Builder
    public Recruit(Company company, String title, String recruitUrl, String job, String empType, String career, String location,
                   String techStack, LocalDateTime openDate, LocalDateTime closeDate, boolean sBenefit, String content) {
        this.company = company;
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
        this.views = 0;
    }

    public void addView() {
        this.views++;
    }

    public void update(Recruit recruit) {
        this.title = recruit.getTitle();
        this.recruitUrl = recruit.getRecruitUrl();
        this.job = recruit.getJob();
        this.empType = recruit.getEmpType();
        this.career = recruit.getCareer();
        this.location = recruit.getLocation();
        this.techStack = recruit.getTechStack();
        this.openDate = recruit.getOpenDate();
        this.closeDate = recruit.getCloseDate();
        this.sBenefit = recruit.isSBenefit();
        this.content = recruit.getContent();
    }

    public boolean toggleRecruitScarp(RecruitScrap recruitScarp) {
        return recruitScraps.toggleRecruitScrap(recruitScarp);
    }

    public int recruitScarpCount(){
        return recruitScraps.size();
    }
}
