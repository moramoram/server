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

    private Boolean likeStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public BoardResponse(Board board) { //전체 조회

        this.boardType = board.getBoardType();

        if (this.boardType == 2)
            this.writerInfo = UserResponse.from_anon(board.getUser());
        else
            this.writerInfo = UserResponse.from(board.getUser());

        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.views = board.getViews();
        this.totalComment = board.getTotalComments();
        this.totalLike = board.getTotalLikes();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }

    public BoardResponse(Board board, Boolean likeStatus) { //단건 조회

        this.boardType = board.getBoardType();

        if (this.boardType == 2) {
            this.writerInfo = UserResponse.from_anon(board.getUser());
            this.comments = board.getBoardComments().getBoardComments().stream()
                    .map(BoardCommentResponse::from_anon).collect(Collectors.toList());
        } else {
            this.writerInfo = UserResponse.from(board.getUser());
            this.comments = board.getBoardComments().getBoardComments().stream()
                    .map(BoardCommentResponse::from).collect(Collectors.toList());
        }

        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.views = board.getViews();
        this.totalComment = board.getTotalComments();
        this.totalLike = board.getTotalLikes();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.likeStatus = likeStatus;
    }

}
