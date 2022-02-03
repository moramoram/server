package com.moram.ssafe.domain.study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudyScrapRepository extends JpaRepository<StudyScrap, Long> {

    Boolean existsByStudyIdAndUserId(Long studyId, Long userId);

    @EntityGraph(attributePaths = {"study"})
    @Query("select s from StudyScrap s where s.user.id = :userId")
    Page<StudyScrap> findUserScrap(@Param("userId") Long userId, Pageable pageable);
}
