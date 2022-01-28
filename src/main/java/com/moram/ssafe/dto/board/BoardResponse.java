package com.moram.ssafe.dto.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.Getter;

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


    public BoardResponse(Board allBoard, Integer totalLike, boolean likeStatus){

        this.writerInfo = UserResponse.from(allBoard.getUser());

        if(allBoard.getBoard_type()==2)
            this.writerInfo.setNickname("익명");

        this.boardId = allBoard.getId();
        this.boardType = allBoard.getBoard_type();
        this.title = allBoard.getTitle();
        this.content = allBoard.getContent();
        this.views = allBoard.getViews();
        this.comments = allBoard.getCommentList().stream().map(comment -> BoardCommentResponse.from(comment)).collect(Collectors.toList());
        this.totalLike = totalLike;
        this.likeStatus = likeStatus;
    }

    public BoardResponse(Board allBoard, Integer totalComment, Integer totalLike) {

        this.writerInfo = UserResponse.from(allBoard.getUser());

        if (allBoard.getBoard_type() == 2)
            this.writerInfo.setNickname("익명");

        this.boardId = allBoard.getId();
        this.boardType = allBoard.getBoard_type();
        this.title = allBoard.getTitle();
        this.content = allBoard.getContent();
        this.views = allBoard.getViews();
        this.totalComment = totalComment;
        this.totalLike = totalLike;
    }
}
