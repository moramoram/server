package com.moram.ssafe.domain.study;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyScrapRepository extends JpaRepository<StudyScrap, Long> {

    boolean existsByStudyIdAndUserId(Long studyId, Long userId);

    @Query("select s from StudyScrap s join fetch s.study where s.user.id = :userId")
    List<StudyScrap> findUserScrap(@Param("userId") Long userId);
}
