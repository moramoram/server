package com.moram.ssafe.dto.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardSaveRequest {

    @Min(value = 1)
    @Max(value = 4)
    private int boardType;

    @NotBlank(message = "제목이 없습니다.")
    @Length(max = 45, message = "45자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    public Board of(User user) {
        return Board.builder().user(user).boardType(boardType).title(title).content(content).build();
    }
}
