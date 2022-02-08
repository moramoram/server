package com.moram.ssafe.exception.study;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class StudyScrapRemovalFailure extends SafeServerException {
    public StudyScrapRemovalFailure() {super(ErrorCode.STUDY_SCRAP_REMOVE_FAIL);}
}
