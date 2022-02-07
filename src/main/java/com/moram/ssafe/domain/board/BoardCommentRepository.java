package com.moram.ssafe.domain.board;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    void deleteByBoardId(Long boardId);

    @Query("select distinct c from BoardComment c join fetch c.user where c.board.id = :boardId")
    List<BoardComment> findBoardCommentList(@Param("boardId") Long boardId);
}
