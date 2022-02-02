package com.moram.ssafe.domain.recruit;

import com.moram.ssafe.dto.recruit.RecruitSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.moram.ssafe.domain.recruit.QRecruit.recruit;
import static com.querydsl.jpa.JPAExpressions.select;

@RequiredArgsConstructor
@Repository
public class RecruitQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Recruit> findByLotsOfScrap(PageRequest pageable) {

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .orderBy(recruit.recruitScraps.recruitScraps.size().desc())
                .offset(pageable.getOffset())
                .limit(4)
                .fetch();
    }

    public List<Recruit> findRecruitCloseDate(PageRequest pageable) {

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .where(recruit.closeDate.goe(LocalDateTime.now()))
                .orderBy(recruit.closeDate.asc())
                .offset(pageable.getOffset())
                .limit(4)
                .fetch();
    }

    public List<Recruit> findRecruitTitleAndTechStack(PageRequest pageable, RecruitSearch recruitSearch) {

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .where(recruit.title.contains(recruitSearch.getTitle()),
                        recruit.job.contains(recruitSearch.getJob())
                        , containsTechStack(recruitSearch.getTechStack()))
                .orderBy(recruit.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(4)
                .fetch();
    }

    public BooleanExpression containsTechStack(List<String> techStack) {
        if (techStack.isEmpty()|| techStack==null) {
            return null;
        }
        return Expressions.stringTemplate("SUBSTRING_INDEX({0},',',1)", recruit.techStack).in(techStack);
    }
}
