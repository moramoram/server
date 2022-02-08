package com.moram.ssafe.exception.study;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class StudyScrapNotFound extends SafeServerException {
    public StudyScrapNotFound() {super(ErrorCode.STUDY_SCRAP_NOT_FOUND);}
}
