package com.moram.ssafe.exception.company;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class CompanyCommentNotFoundException extends SafeServerException {
    public CompanyCommentNotFoundException() {
        super(ErrorCode.COMPANY_COMMENT_NOT_FOUND);
    }
}
