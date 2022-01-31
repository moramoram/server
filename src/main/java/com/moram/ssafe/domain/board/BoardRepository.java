package com.moram.ssafe.domain.board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query("select b from Board b where b.board_type = :boardType")
    Page<Board> findAll(@Param("boardType") int boardType, Pageable pageable);

    @Query("select b from Board b join fetch b.user where b.id = :boardId")
    Optional<Board> findById(@Param("boardId") Long boardId);

    @Query("select b from Board b join fetch b.user where b.user.id = :userId")
    List<Board> findByUserId(@Param("userId") Long userId);

    Page<Board> findByTitleContaining(@Param("name") String name, Pageable pageable);

}