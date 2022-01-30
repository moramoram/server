package com.moram.ssafe.domain.study;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.dto.study.StudyUpdateRequest;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "study")
    private List<StudyComment> commentList = new ArrayList<>();

    private String company_name;

    private String title;

    private String study_type;

    private String tech_stack;

    private Integer recruitment;

    private String location;

    private Integer on_off;

    private String content;

    private Integer views;

    public void addView() {this.views++;}

    public void addComment(StudyComment comment){
        commentList.add(comment);
        comment.setStudy(this);
    }

    public void update(StudyUpdateRequest updateRequest){
        this.company_name = updateRequest.getCompany_name();
        this.title = updateRequest.getTitle();
        this.study_type = updateRequest.getStudy_type();
        this.tech_stack = updateRequest.getTech_stack();
        this.recruitment = updateRequest.getRecruitment();
        this.location = updateRequest.getLocation();
        this.on_off = updateRequest.getOn_off();
        this.content = updateRequest.getContent();
    }

    @Builder
    public Study(User user, String company_name, String title, String study_type,
                 String tech_stack, Integer recruitment, String location,
                 Integer on_off, String content){
        this.user = user;
        this.company_name = company_name;
        this.title = title;
        this.study_type = study_type;
        this.tech_stack = tech_stack;
        this.recruitment = recruitment;
        this.location = location;
        this.on_off = on_off;
        this.content = content;
        this.views = 0;
    }
}
