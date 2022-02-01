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
    Page<Study> findAll(Pageable pageable);

    @Query("select s from Study s join fetch s.user where s.id = :studyId")
    Optional<Study> findById(@Param("studyId") Long studyId);

    @Query("select s from Study s join fetch s.user where s.user.id = :userId")
    List<Study> findByUserId(@Param("userId") Long userId);

    @EntityGraph(attributePaths = {"user"})
    @Query("select s from Study s where s.title like %:name%")
    Page<Study> findByTitleContaining(@Param("name")String name, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select s from Study s order by s.scrapList.size desc")
    Page<Study> findByLotsOfScrap(Pageable pageable);

}
