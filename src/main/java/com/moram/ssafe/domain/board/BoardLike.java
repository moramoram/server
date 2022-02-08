package com.moram.ssafe.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_all_board_like")
public class BoardLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public BoardLike(Board board, Long userId){
        this.board = board;
        this.userId = userId;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean ownedBy(Long userId) {
        return this.userId.equals(userId);
    }
}
