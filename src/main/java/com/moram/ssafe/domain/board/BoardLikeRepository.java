package com.moram.ssafe.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    boolean existsByBoardIdAndUserId(Long boardId, Long userId);
}
