package com.moram.ssafe.exception.user;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class DuplicateNicknameException extends SafeServerException {
    public DuplicateNicknameException() {
        super(ErrorCode.DUPLICATE_NICKNAME);
    }
}
