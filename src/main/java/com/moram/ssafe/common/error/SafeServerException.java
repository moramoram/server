package com.moram.ssafe.common.error;

import lombok.Getter;

@Getter
public class SafeServerException extends RuntimeException {

    private ErrorCode errorCode;

    public SafeServerException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
