package com.moram.ssafe.domain.study;

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
@Table(name = "tbl_study_scrap")
public class StudyScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public StudyScrap(Long userId, Study study){
        this.userId = userId;
        this.study = study;
    }

    public boolean ownedBy(Long userId) {return this.userId.equals(userId);}
}
