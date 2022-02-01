package com.moram.ssafe.domain.recruit;

import com.moram.ssafe.dto.recruit.RecruitSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
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
                        containsTechStack(recruitSearch.getTechStack()),
                        recruit.job.contains(recruitSearch.getJob()))
                .orderBy(recruit.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(4)
                .fetch();
    }

    public BooleanExpression containsTechStack(List<String> techStack){
        return recruit.techStack.in(techStack);
    }
}
