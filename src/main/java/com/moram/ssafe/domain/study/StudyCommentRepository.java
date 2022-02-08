package com.moram.ssafe.domain.study;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

    @Transactional
    @Modifying
    @Query("delete from StudyComment c where c.study.id = :studyId")
    void deleteByStudyId(@Param("studyId")Long studyId);

    @Query("select distinct s from StudyComment s join fetch s.user where s.study.id = :studyId")
    List<StudyComment> findStudyComment(@Param("studyId") Long studyId);

}
