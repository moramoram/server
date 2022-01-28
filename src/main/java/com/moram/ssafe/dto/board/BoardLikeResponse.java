package com.moram.ssafe.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardLikeResponse {

    private Long boardId;

    public static BoardLikeResponse of(Long boardId){
        return new BoardLikeResponse(boardId);
    }
}
