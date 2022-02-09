package com.moram.ssafe.domain.recruit;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit,Long> {

    @Override
    @EntityGraph(attributePaths = {"company"})
    Optional<Recruit> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"company"})
    List<Recruit> findAll();

    @EntityGraph(attributePaths = {"company"})
    @Query(value = "select r from Recruit r join RecruitScrap rc " +
            "on r.id = rc.recruit.id and rc.userId = :userId")
    List<Recruit> findByUserScrap(@Param("userId") Long userId);

    @EntityGraph(attributePaths = {"company"})
    @Query(value = "select r from Recruit r " +
            "where r.sBenefit = true")
    List<Recruit> findByRecruitBenefit(Pageable pageable);

    @EntityGraph(attributePaths = {"company"})
    @Query(value = "select r from Recruit r")
    List<Recruit> findByRecruitLatest(Pageable pageable);

    void deleteByCompanyId(Long companyId);
}
