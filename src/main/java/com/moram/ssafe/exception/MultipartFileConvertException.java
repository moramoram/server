package com.moram.ssafe.exception;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class MultipartFileConvertException extends SafeServerException {
    public MultipartFileConvertException() {
        super(ErrorCode.MULTIPART_FILE_CONVERT_FAIL);
    }
}
