package com.moram.ssafe.dto.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardSaveRequest {

    @Min(value=1) @Max(value=4)
   private int board_type;

    @NotBlank(message = "제목이 없습니다.")
    private String title;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public Board of(User user){
        return Board.builder().user(user).board_type(board_type).title(title).content(content).build();
    }
}
