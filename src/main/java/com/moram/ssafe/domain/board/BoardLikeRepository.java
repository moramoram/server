package com.moram.ssafe.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    @Query("select l from BoardLike l join fetch l.user where l.id = :likeId")
    Optional<BoardLike> findById(@Param("likeId") Long likeId);

    Boolean existsByBoardIdAndUserId(Long boardId, Long userId);
}
