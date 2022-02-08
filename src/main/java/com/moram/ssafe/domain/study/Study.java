package com.moram.ssafe.domain.study;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.dto.study.StudyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tbl_study")
public class Study extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<StudyComment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<StudyScrap> scrapList = new ArrayList<>();

    private String companyName;

    private String title;

    private String studyType;

    private String techStack;

    private Boolean recruitment;

    private String memberNumber;

    private String thumbnailImg;

    private Integer onOff;

    private String content;

    private Integer views;

    public void addView() {
        this.views++;
    }

    public void addComment(StudyComment comment) {
        commentList.add(comment);
        comment.setStudy(this);
    }

    public void update(StudyRequestDto requestDto) {
        this.companyName = requestDto.getCompanyName();
        this.title = requestDto.getTitle();
        this.studyType = requestDto.getStudyType();
        this.techStack = requestDto.getTechStack();
        this.memberNumber = requestDto.getMemberNumber();
        this.thumbnailImg = requestDto.getThumbnailImg();
        this.onOff = requestDto.getOnOff();
        this.content = requestDto.getContent();
    }

    @Builder
    public Study(User user, String companyName, String title, String studyType,
                 String techStack, String memberNumber, String thumbnailImg,
                 Integer onOff, String content) {
        this.user = user;
        this.companyName = companyName;
        this.title = title;
        this.studyType = studyType;
        this.techStack = techStack;
        this.recruitment = true;
        this.memberNumber = memberNumber;
        this.thumbnailImg = thumbnailImg;
        this.onOff = onOff;
        this.content = content;
        this.views = 0;
    }

    public Boolean updateRecruitment(boolean recruitment) {
        this.recruitment = recruitment;
        return recruitment;
    }
}
