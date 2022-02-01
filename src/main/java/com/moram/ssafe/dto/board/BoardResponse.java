package com.moram.ssafe.dto.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BoardResponse {

    private Long boardId;

    private Integer boardType;

    private UserResponse writerInfo;

    private String title;

    private String content;

    private Integer views;

    private List<BoardCommentResponse> comments = new ArrayList<>();

    private Integer totalComment;

    private Integer totalLike;

    private boolean likeStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    public BoardResponse(Board board, Integer totalLike, boolean likeStatus){

        this.writerInfo = UserResponse.from(board.getUser());

        if(board.getBoard_type()==2)
            this.writerInfo.setNickname("익명");

        this.boardId = board.getId();
        this.boardType = board.getBoard_type();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.views = board.getViews();
        this.comments = board.getCommentList().stream()
                .map(comment -> BoardCommentResponse.from(comment)).collect(Collectors.toList());
        this.totalLike = totalLike;
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.likeStatus = likeStatus;
    }

    public BoardResponse(Board board, Integer totalComment, Integer totalLike) {

        this.writerInfo = UserResponse.from(board.getUser());

        if (board.getBoard_type() == 2)
            this.writerInfo.setNickname("익명");

        this.boardId = board.getId();
        this.boardType = board.getBoard_type();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.views = board.getViews();
        this.totalComment = totalComment;
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.totalLike = totalLike;
    }
}
