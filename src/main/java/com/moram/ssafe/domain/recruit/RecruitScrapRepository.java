package com.moram.ssafe.domain.recruit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitScrapRepository extends JpaRepository<RecruitScrap, Long> {
    boolean existsByUserIdAndRecruitId(Long userId,Long recruitId);
}
