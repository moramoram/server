package com.moram.ssafe.exception.company;

import com.moram.ssafe.exception.error.ErrorCode;
import com.moram.ssafe.exception.error.SafeServerException;

public class CompanyNotFoundException extends SafeServerException {
    public CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
