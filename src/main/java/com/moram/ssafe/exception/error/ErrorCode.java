package com.moram.ssafe.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moram.ssafe.exception.auth.UnsupportedJwtTokenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400 , "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    ENTITY_NOT_FOUND(400, " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),
    INVALID_TYPE_VALUE(400, " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "Access is Denied"),
    JSON_WRITE_ERROR(401, "JSON content that are not pure I/O problems"),

    //User
    USER_NOT_FOUND(404, "User not found."),
    DUPLICATE_NICKNAME(409,"Duplicate nickname."),

    //Auth and Jwt
    FAIL_TO_AUTHENTICATION(401,"Fail To Authentication"),
    NOT_EXIST_TOKEN(401,"Not exist token"),
    EXPIRED_ACCESS_TOKEN(401, "Expired access token."),
    INVALID_JWT_SIGNATURE(401,"Invalid JWT signature"),
    UNSUPPORTED_JWT_TOKEN(401,"Unsupported Jwt Token"),
    INVALID_JWT_TOKEN(401,"Invalid Jwt token"),
    NOT_VALID_USER(401,"Not valid user"),

    //Recruit
    RECRUIT_NOT_FOUND(404,"Recruit not found"),
    RECRUIT_SCRAP_REMOVE_FAIL(404,"Recruit Scrap Remove fail"),

    //Company
    COMPANY_NOT_FOUND(404,"Company not found"),
    COMPANY_COMMENT_NOT_FOUND(404,"Company comment not found"),

    //Board
    BOARD_NOT_FOUND(404, "Board not found"),
    LIKE_REMOVE_FAIL(404, "Board Like remove fail"),

    //Comment
    COMMENT_NOT_FOUND(404, "Comment not found"),

    //Study
    STUDY_NOT_FOUND(404, "Study not found"),
    STUDY_SCRAP_NOT_FOUND(404, "Study Scrap not found");

    private final int status;
    private final String message;
}
