package com.moram.ssafe.dto.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class BoardSaveRequest {

    @Min(value=1) @Max(value=4)
   private int boardType;

    @NotBlank(message = "제목이 없습니다.")
    @Size(max = 45, message = "1자 이상 45자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public Board of(User user){
        return Board.builder().user(user).boardType(boardType).title(title).content(content).build();
    }
}
