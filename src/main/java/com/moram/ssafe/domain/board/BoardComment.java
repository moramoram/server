package com.moram.ssafe.domain.board;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_all_board_comment")
public class BoardComment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    private String content;

    public void setBoard(Board board) {
        this.board = board;
    }

    @Builder
    public BoardComment(User user, Board board, String content) {
        this.user = user;
        this.board = board;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
