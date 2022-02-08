package com.moram.ssafe.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    @Transactional
    @Modifying
    @Query("delete from BoardLike l where l.board.id = :boardId")
    void deleteByBoardId(@Param("boardId") Long boardId);

    Boolean existsByBoardIdAndUserId(Long boardId, Long userId);
}
