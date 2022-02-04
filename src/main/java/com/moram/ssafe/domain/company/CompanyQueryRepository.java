package com.moram.ssafe.domain.company;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.company.QCompanyComment.companyComment;

@RequiredArgsConstructor
@Repository
public class CompanyQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Company> findUserCommentsByCompany(Long userId) {

        return jpaQueryFactory
                .select(companyComment.company)
                .distinct()
                .from(companyComment)
                .where(companyComment.user.id.eq(userId),
                        companyComment.company.id.eq(companyComment.company.id))
                .fetch();
    }
    
}

