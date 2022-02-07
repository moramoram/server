package com.moram.ssafe.domain.board;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tbl_all_board")
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

   @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardComment> commentList = new ArrayList<>();

   @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardLike> likeList = new ArrayList<>();

    private Integer boardType; //1:자유 2:익명 3:취업정보 4:질문

    private String title;

    private String content;

    private Integer views;

    public void addView() {
        this.views++;
    }

    public void addComment(BoardComment comment){
        this.commentList.add(comment);
        comment.setBoard(this);
    }

    public void addLike(BoardLike like){
        this.likeList.add(like);
        like.setBoard(this);
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Builder
    public Board(User user, Integer boardType, String title, String content){
        this.user = user;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.views=0;
    }

}
