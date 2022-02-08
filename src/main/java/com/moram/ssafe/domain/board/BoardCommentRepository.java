package com.moram.ssafe.domain.board;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    @Transactional
    @Modifying
    @Query("delete from BoardComment c where c.board.id = :boardId")
    void deleteByBoardId(@Param("boardId") Long boardId);

    @Query("select distinct c from BoardComment c join fetch c.user where c.board.id = :boardId")
    List<BoardComment> findBoardCommentList(@Param("boardId") Long boardId);
}
