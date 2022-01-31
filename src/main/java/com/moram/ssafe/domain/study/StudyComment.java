package com.moram.ssafe.domain.study;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tbl_study_comment")
public class StudyComment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    public void setStudy(Study study) {
        this.study = study;
    }

    @Builder
    public StudyComment(User user, Study study, String content){
        this.user = user;
        this.study = study;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
