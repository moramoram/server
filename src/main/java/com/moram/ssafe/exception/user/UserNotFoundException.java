package com.moram.ssafe.exception.user;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class UserNotFoundException extends SafeServerException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
