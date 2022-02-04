package com.moram.ssafe.domain.study;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

    Optional<StudyComment> findById(Long commentId);

    @Query("select s from StudyComment s join fetch s.user where s.study.id = :studyId")
    List<StudyComment> findStudyComment(@Param("studyId") Long studyId);

}
