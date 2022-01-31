package com.moram.ssafe.exception.study;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class StudyCommentNotFound extends SafeServerException {
    public StudyCommentNotFound() {super(ErrorCode.COMMENT_NOT_FOUND);}
}
