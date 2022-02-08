package com.moram.ssafe.exception.board;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class BoardLikeRemovalFailure extends SafeServerException {
    public BoardLikeRemovalFailure(){super(ErrorCode.LIKE_REMOVE_FAIL);}
}
