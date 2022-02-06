package com.moram.ssafe.domain.recruit;

import com.moram.ssafe.dto.recruit.RecruitSearch;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.moram.ssafe.domain.recruit.QRecruit.recruit;

@RequiredArgsConstructor
@Repository
public class RecruitQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Recruit> findByLotsOfScrap(PageRequest pageable) {

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .orderBy(recruit.recruitScraps.recruitScraps.size().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public List<Recruit> findRecruitCloseDate(PageRequest pageable) {

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .where(recruit.closeDate.goe(LocalDateTime.now()))
                .orderBy(recruit.closeDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public List<Recruit> findRecruitTitleAndTechStack(PageRequest pageable, RecruitSearch recruitSearch) {

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .where(containTitle(recruitSearch.getTitle()),
                        containJob(recruitSearch.getJob())
                        , containTechStack(recruitSearch.getTechStack()))
                .orderBy(findCriteria(recruitSearch.getCriteria()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression containTitle(String title) {
        if (title.isEmpty() || title == null) {
            return null;
        }
        return recruit.title.contains(title);
    }

    private BooleanExpression containJob(String job) {
        if (job.isEmpty() || job == null) {
            return null;
        }
        return recruit.job.contains(job);
    }

    private BooleanExpression containTechStack(List<String> techStack) {
        if (techStack.isEmpty() || techStack == null) {
            return null;
        }
        return Expressions.anyOf(techStack.stream().map(this::isFilteredTechStack).toArray(BooleanExpression[]::new));
    }

    private BooleanExpression isFilteredTechStack(String techStack) {
        return recruit.techStack.contains(techStack);
    }

    private OrderSpecifier<?> findCriteria(String criteria) {
        if (criteria.equals("date")) {
            return recruit.createdDate.desc();
        }

        if (criteria.equals("scrap")) {
            return recruit.recruitScraps.recruitScraps.size().desc();
        }
        return recruit.createdDate.desc();
    }
}
