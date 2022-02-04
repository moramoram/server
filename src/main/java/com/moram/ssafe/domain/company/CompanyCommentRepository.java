package com.moram.ssafe.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyCommentRepository extends JpaRepository<CompanyComment, Long> {

    List<CompanyComment> findByCompanyId(Long companyId);
}
