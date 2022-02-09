package com.moram.ssafe.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompanyCommentRepository extends JpaRepository<CompanyComment, Long> {

    List<CompanyComment> findByCompanyId(Long companyId);

    @Transactional
    @Modifying
    @Query("delete from tbl_company_comment c where c.company.id = :companyId")
    void deleteByCompanyId(@Param("companyId") Long companyId);
}
