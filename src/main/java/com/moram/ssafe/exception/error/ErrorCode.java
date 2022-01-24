package com.moram.ssafe.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moram.ssafe.exception.auth.UnsupportedJwtTokenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "Invalid input value."),

    //User
    USER_NOT_FOUND(404, "User not found."),

    //Auth and Jwt
    FAIL_TO_AUTHENTICATION(401,"Fail To Authentication"),
    NOT_EXIST_TOKEN(401,"Not exist token"),
    EXPIRED_ACCESS_TOKEN(401, "Expired access token."),
    INVALID_JWT_SIGNATURE(401,"Invalid JWT signature"),
    UNSUPPORTED_JWT_TOKEN(401,"Unsupported Jwt Token"),
    INVALID_JWT_TOKEN(401,"Invalid Jwt token"),
    NOT_VALID_USER(401,"Not valid user");


    private final int status;
    private final String message;
}
