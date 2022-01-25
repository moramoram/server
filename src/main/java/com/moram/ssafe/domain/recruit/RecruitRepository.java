package com.moram.ssafe.domain.recruit;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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


}
