package com.moram.ssafe.dto.board;

import com.moram.ssafe.domain.board.BoardComment;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardCommentResponse {

    private Long commentId;
    private String content;

    private UserResponse userInfo;

    public static BoardCommentResponse from(BoardComment comment){
        return BoardCommentResponse.builder().commentId(comment.getId()).content(comment.getContent())
                .userInfo(UserResponse.from(comment.getUser())).build();
    }


}
