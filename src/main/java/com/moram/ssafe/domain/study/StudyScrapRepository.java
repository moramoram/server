package com.moram.ssafe.domain.study;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyScrapRepository extends JpaRepository<StudyScrap, Long> {

    Boolean existsByStudyIdAndUserId(Long studyId, Long userId);

}
