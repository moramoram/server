package com.moram.ssafe.domain.recruit;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_recruit_scrap")
public class RecruitScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public RecruitScrap(Recruit recruit, Long userId) {
        this.recruit = recruit;
        this.userId = userId;
    }

    public boolean ownedBy(Long userId) {
        return this.userId.equals(userId);
    }
}
