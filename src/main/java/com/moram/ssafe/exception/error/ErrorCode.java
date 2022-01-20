package com.moram.ssafe.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "Invalid input value."),
    HELLO_ERROR_MESSAGE(404,"Hello 요청 실패"),
    USER_NOT_FOUND(404, "User not found.");

    private final int status;
    private final String message;
}
