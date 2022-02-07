package com.moram.ssafe.domain.study;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.study.QStudyScrap.studyScrap;

@RequiredArgsConstructor
@Repository
public class StudyScrapQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Study> findByUserScrap(Long userId, PageRequest pageable){
        return jpaQueryFactory
                .select(studyScrap.study)
                .distinct()
                .from(studyScrap)
                .where(studyScrap.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
