package com.moram.ssafe.exception.error;

import lombok.Getter;

@Getter
public class SafeServerException extends RuntimeException {

    private ErrorCode errorCode;

    public SafeServerException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
