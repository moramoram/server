package com.moram.ssafe.domain.board;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query("select b from Board b where b.id = :boardId")
    Optional<Board> findBoard(@Param("boardId") Long boardId);
}