package com.moram.ssafe.domain.recruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RecruitScrapRepository extends JpaRepository<RecruitScrap, Long> {
    boolean existsByUserIdAndRecruitId(Long userId,Long recruitId);

    @Transactional
    @Modifying
    @Query("delete from RecruitScrap s where s.recruit.id = :recruitId")
    void deleteByRecruitId(@Param("recruitId") Long recruitId);
}
