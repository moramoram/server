package com.moram.ssafe.exception.recruit;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class RecruitNotFoundException extends SafeServerException {
    public RecruitNotFoundException() {
        super(ErrorCode.RECRUIT_NOT_FOUND);
    }
}
