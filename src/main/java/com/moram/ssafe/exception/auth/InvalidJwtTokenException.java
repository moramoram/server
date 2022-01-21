package com.moram.ssafe.exception.auth;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class InvalidJwtTokenException extends SafeServerException {
    public InvalidJwtTokenException() {
        super(ErrorCode.INVALID_JWT_TOKEN);
    }
}
