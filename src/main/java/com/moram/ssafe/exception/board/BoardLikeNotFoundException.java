package com.moram.ssafe.exception.board;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class BoardLikeNotFoundException extends SafeServerException {
    public BoardLikeNotFoundException(){super(ErrorCode.LIKE_NOT_FOUND);}
}
