package com.moram.ssafe.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"commentList"})
    @Query("select distinct b from Board b where b.boardType = :boardType")
    Page<Board> findBoardList(@Param("boardType") int boardType, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "commentList"})
    @Query("select b from Board b where b.id = :boardId")
    Optional<Board> findBoard(@Param("boardId") Long boardId);

    @EntityGraph(attributePaths = {"commentList"})
    @Query("select distinct b from Board b where b.user.id = :userId")
    Page<Board> findUserBoard(@Param("userId") Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct b from Board b where b.boardType = :boardType and b.title like %:name%")
    Page<Board> findByTitleContaining(@Param("boardType") int boardType, @Param("name") String name,
                                      Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select distinct b from Board b where b.boardType = :boardType " +
            "order by size(b.likeList) desc")
    Page<Board> findByLotsOfLike(@Param("boardType") int boardType, Pageable pageable);
}