package com.moram.ssafe.domain.board;

import com.moram.ssafe.dto.board.BoardSearch;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moram.ssafe.domain.board.QBoard.board;
import static com.moram.ssafe.domain.board.QBoardComment.boardComment;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Board> findBoardList(int boardType, PageRequest pageRequest){
        return jpaQueryFactory.select(board)
                .distinct().from(board)
                .innerJoin(board.user).fetchJoin()
                .where(board.boardType.eq(boardType))
                .orderBy(board.createdDate.desc())
                .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize())
                .fetch();
    }

    public List<Board> findByUserComment(Long userId){
        return jpaQueryFactory
                .select(boardComment.board)
                .distinct()
                .from(boardComment)
                .innerJoin(boardComment.user)
                .where(boardComment.user.id.eq(userId))
                .orderBy(boardComment.createdDate.desc()).fetch();
    }

    public List<Board> findUserBoard(Long userId, PageRequest pageRequest){
        return jpaQueryFactory.selectFrom(board).distinct()
                .innerJoin(board.user).fetchJoin()
                .where(board.user.id.eq(userId))
                .orderBy(board.createdDate.desc())
                .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize())
                .fetch();
    }

    public List<Board> findByLotsOfView(int boardType, PageRequest pageRequest){
        return jpaQueryFactory.selectFrom(board).distinct()
                .innerJoin(board.user).fetchJoin()
                .where(board.boardType.eq(boardType))
                .orderBy(board.views.desc())
                .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize())
                .fetch();
    }

    public List<Board> findByLotsOfLike(int boardType, PageRequest pageRequest){
        return jpaQueryFactory.selectFrom(board).distinct()
                .innerJoin(board.user).fetchJoin()
                .where(board.boardType.eq(boardType))
                .orderBy(board.boardLikes.boardLikes.size().desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }

    public List<Board> findByTitleContaining(int boardType, String name, PageRequest pageRequest){
        return jpaQueryFactory.selectFrom(board).distinct()
                .innerJoin(board.user).fetchJoin()
                .where(board.boardType.eq(boardType), board.title.contains(name))
                .orderBy(board.createdDate.desc())
                .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize())
                .fetch();
    }

    public List<Board> searchBoard(PageRequest pageRequest, BoardSearch boardSearch){
        return jpaQueryFactory.selectFrom(board)
                .innerJoin(board.user).fetchJoin()
                .where(board.title.contains(boardSearch.getTitle()),
                        board.boardType.eq(boardSearch.getBoardType()))
                .orderBy(findCriteria(boardSearch.getCriteria()))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }

    private OrderSpecifier<?> findCriteria(String criteria){
        if(criteria.equals("date")){
            return board.createdDate.desc();
        } else if(criteria.equals("like")){
            return board.boardLikes.boardLikes.size().desc();
        } else if(criteria.equals("view")){
            return board.views.desc();
        }

        return board.createdDate.desc();
    }
}
