package com.moram.ssafe.exception.recruit;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class RecruitScrapRemovalFailureException extends SafeServerException {
    public RecruitScrapRemovalFailureException() {
        super(ErrorCode.RECRUIT_SCRAP_REMOVE_FAIL);
    }

}
