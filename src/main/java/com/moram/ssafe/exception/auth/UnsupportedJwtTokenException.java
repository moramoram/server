package com.moram.ssafe.exception.auth;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class UnsupportedJwtTokenException extends SafeServerException {
    public UnsupportedJwtTokenException(){
        super(ErrorCode.UNSUPPORTED_JWT_TOKEN);
    }
}
