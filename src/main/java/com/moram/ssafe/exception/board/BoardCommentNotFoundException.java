package com.moram.ssafe.exception.board;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class BoardCommentNotFoundException extends SafeServerException {
    public BoardCommentNotFoundException() {super(ErrorCode.COMMENT_NOT_FOUND);}
}
