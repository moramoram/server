package com.moram.ssafe.domain.study;

import com.moram.ssafe.dto.study.StudySearch;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.study.QStudy.study;
import static com.moram.ssafe.domain.study.QStudyComment.studyComment;

@RequiredArgsConstructor
@Repository
public class StudyQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Study> findByUserComment(Long userId) {
        return jpaQueryFactory
                .select(studyComment.study)
                .distinct()
                .from(studyComment)
                .where(studyComment.user.id.eq(userId)).fetch();
    }

    public List<Study> findByTitleAndTypeContaining(StudySearch studySearch, PageRequest pageRequest) {
        return jpaQueryFactory.selectFrom(study)
                .innerJoin(study.user).fetchJoin()
                .where(containTitle(studySearch.getTitle())
                        , containType(studySearch.getStudyType()))
                .orderBy(findCriteria(studySearch.getCriteria()))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }

    public List<Study> findCompanyNameStudy(String companyName) {
        return jpaQueryFactory.selectFrom(study)
                .innerJoin(study.user).fetchJoin()
                .where(containCompanyName(companyName))
                .orderBy(study.companyName.asc())
                .fetch();
    }

    private BooleanExpression containCompanyName(String companyName) {
        if (companyName.isEmpty() || companyName == null)
            return null;
        return study.companyName.contains(companyName);
    }

    private BooleanExpression containTitle(String title) {
        if (title.isEmpty() || title == null)
            return null;
        return study.title.contains(title);
    }

    private BooleanExpression containType(String type) {
        if (type.isEmpty() || type == null)
            return null;
        return study.studyType.eq(type);
    }

    private OrderSpecifier<?> findCriteria(String criteria) {
        if (criteria.equals("date")) {
            return study.createdDate.desc();
        }
        if (criteria.equals("scrap")) {
            return study.studyScraps.studyScraps.size().desc();
        }
        return study.createdDate.desc();
    }


}
