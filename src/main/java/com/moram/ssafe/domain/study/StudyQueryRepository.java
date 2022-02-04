package com.moram.ssafe.domain.study;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.study.QStudyComment.studyComment;

@RequiredArgsConstructor
@Repository
public class StudyQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

//    public List<Study> findStudyTitleAndKind(PageRequest pageable, StudySearch studySearch){
//    }

    public List<Study> findByUserComment(Long userId){
        return jpaQueryFactory
                .select(studyComment.study)
                .distinct()
                .from(studyComment)
                .where(studyComment.user.id.eq(userId)).fetch();
    }


}
