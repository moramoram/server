package com.moram.ssafe.exception.study;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class StudyNotFoundException extends SafeServerException {
    public StudyNotFoundException() {super(ErrorCode.STUDY_NOT_FOUND);}
}
