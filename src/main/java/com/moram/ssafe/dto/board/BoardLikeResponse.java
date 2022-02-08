package com.moram.ssafe.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardLikeResponse {
    private boolean likeStatus;

    public static BoardLikeResponse from(boolean like) {
        return new BoardLikeResponse(like);
    }
}
