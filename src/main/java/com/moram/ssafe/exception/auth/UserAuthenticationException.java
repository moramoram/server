package com.moram.ssafe.exception.auth;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class UserAuthenticationException extends SafeServerException {
    public UserAuthenticationException() {
        super(ErrorCode.NOT_VALID_USER);
    }
}
