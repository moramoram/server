package com.moram.ssafe.domain.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.board.QBoardComment.boardComment;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Board> findByUserComment(Long userId){
        return jpaQueryFactory
                .select(boardComment.board)
                .distinct()
                .from(boardComment)
                .where(boardComment.user.id.eq(userId)).fetch();
    }
}
