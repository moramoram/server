package com.moram.ssafe.dto.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardComment;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class BoardCommentSaveRequest {

    @NotNull(message = "게시물 아이디가 없습니다.")
    private Long boardId;

    @NotBlank(message = "게시물 댓글 내용이 없습니다.")
    private String content;

    public BoardComment from(User user, Board board, String content){
        return BoardComment.builder().user(user).board(board).content(content).build();
    }
}
