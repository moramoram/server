package com.moram.ssafe.dto.board;

import com.moram.ssafe.domain.board.BoardComment;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardCommentResponse {

    private Long commentId;

    private String content;

    private UserResponse userInfo;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public static BoardCommentResponse from(BoardComment comment){
        return BoardCommentResponse.builder().commentId(comment.getId()).content(comment.getContent())
                .userInfo(UserResponse.from(comment.getUser())).createdDate(comment.getCreatedDate()).modifiedDate(comment.getModifiedDate()).build();
    }

    public static BoardCommentResponse from_anon(BoardComment comment){
        return BoardCommentResponse.builder().commentId(comment.getId()).content(comment.getContent())
                .userInfo(UserResponse.from_anon(comment.getUser())).createdDate(comment.getCreatedDate()).modifiedDate(comment.getModifiedDate()).build();
    }
}
