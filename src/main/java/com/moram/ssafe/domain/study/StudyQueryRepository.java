package com.moram.ssafe.domain.study;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.study.QStudyComment.studyComment;
import static com.moram.ssafe.domain.study.QStudy.study;

@RequiredArgsConstructor
@Repository
public class StudyQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Study> findByUserComment(Long userId){
        return jpaQueryFactory
                .select(studyComment.study)
                .distinct()
                .from(studyComment)
                .where(studyComment.user.id.eq(userId)).fetch();
    }

    public List<Study> findByTitleAndTypeContaining(String title, String type, PageRequest pageRequest){
        return jpaQueryFactory.selectFrom(study)
                .innerJoin(study.user).fetchJoin()
                .where(containTitle(title), containType(type))
                .orderBy(study.createdDate.desc())
                .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize())
                .fetch();
    }

    private BooleanExpression containTitle(String title){
        if(title.isEmpty() || title == null)
            return null;
        return study.title.contains(title);
    }

    private BooleanExpression containType(String type){
        if(type.isEmpty() || type == null)
            return null;
        return study.studyType.eq(type);
    }

}
