package com.moram.ssafe.exception.board;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class BoardNotFoundException extends SafeServerException {
    public BoardNotFoundException() {super(ErrorCode.BOARD_NOT_FOUND);}
}
