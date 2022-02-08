package com.moram.ssafe.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardSearch {

    private Integer boardType;

    private String title;

    private String criteria;

    public static BoardSearch of(Integer boardType, String title, String criteria){
        return new BoardSearch(boardType, title, criteria);
    }
}
