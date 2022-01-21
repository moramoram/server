package com.moram.ssafe.exception.auth;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class NoTokenException extends SafeServerException {
    public NoTokenException() {
        super(ErrorCode.NOT_EXIST_TOKEN);
    }
}
