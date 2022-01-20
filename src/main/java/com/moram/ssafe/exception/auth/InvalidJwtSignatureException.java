package com.moram.ssafe.exception.auth;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class InvalidJwtSignatureException extends SafeServerException {
    public InvalidJwtSignatureException() {
        super(ErrorCode.INVALID_JWT_SIGNATURE);
    }
}
