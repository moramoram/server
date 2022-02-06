package com.moram.ssafe.domain.study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudyScrapRepository extends JpaRepository<StudyScrap, Long> {

    @Query("select s from StudyScrap s join fetch s.user where s.id = :scrapId")
    Optional<StudyScrap> findById(@Param("scrapId")Long scrapId);

    Boolean existsByStudyIdAndUserId(Long studyId, Long userId);

    @EntityGraph(attributePaths = {"study"})
    @Query("select s from StudyScrap s where s.user.id = :userId")
    Page<StudyScrap> findUserScrap(@Param("userId") Long userId, Pageable pageable);
}
