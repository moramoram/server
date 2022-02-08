package com.moram.ssafe.domain.study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct s from Study s")
    Page<Study> findAll(Pageable pageable);

    @Query("select s from Study s join fetch s.user where s.id = :studyId")
    Optional<Study> findStudy(@Param("studyId") Long studyId);

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct s from Study s where s.user.id = :userId")
    Page<Study> findUserStudy(@Param("userId") Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct s from Study s where s.title like %:title%")
    Page<Study> findByTitleContaining(@Param("title") String title, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct s from Study s where s.studyType = :type and s.title like %:title%")
    Page<Study> findByTitleAndTypeContaining(@Param("title")String title, @Param("type") String type,
                                             Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct s from Study s where s.studyType = :type")
    Page<Study> findByTypeContaining(@Param("type") String type, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select s from Study s order by size(s.scrapList) desc")
    Page<Study> findByLotsOfScrap(Pageable pageable);
}
