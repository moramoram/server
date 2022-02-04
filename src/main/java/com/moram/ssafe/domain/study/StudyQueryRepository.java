package com.moram.ssafe.domain.study;

import com.moram.ssafe.dto.study.StudySearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.recruit.QRecruit.recruit;

@RequiredArgsConstructor
@Repository
public class StudyQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

//    public List<Study> findStudyTitleAndKind(PageRequest pageable, StudySearch studySearch){
//    }


}
