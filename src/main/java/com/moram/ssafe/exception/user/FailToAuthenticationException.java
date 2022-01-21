package com.moram.ssafe.exception.user;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class FailToAuthenticationException extends SafeServerException {
    public FailToAuthenticationException() {
        super(ErrorCode.FAIL_TO_AUTHENTICATION);
    }
}

