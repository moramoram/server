package com.moram.ssafe.domain.recruit;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.recruit.QRecruit.recruit;

@RequiredArgsConstructor
@Repository
public class RecruitQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Recruit> findByLotsOfScrap(PageRequest pageable){

        return jpaQueryFactory.selectFrom(recruit)
                .innerJoin(recruit.company).fetchJoin()
                .orderBy(recruit.recruitScraps.recruitScraps.size().desc())
                .offset(pageable.getOffset())
                .limit(4)
                .fetch();
    }
}
