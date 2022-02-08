package com.moram.ssafe.domain.study;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudyScrapRepository extends JpaRepository<StudyScrap, Long> {

    @Transactional
    @Modifying
    @Query("delete from StudyScrap s where s.study.id = :studyId")
    void deleteByStudyId(@Param("studyId")Long studyId);

    Boolean existsByStudyIdAndUserId(Long studyId, Long userId);

}
