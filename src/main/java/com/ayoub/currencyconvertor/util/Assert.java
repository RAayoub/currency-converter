package com.ayoub.currencyconvertor.util;

import com.ayoub.currencyconvertor.exception.BusinessException;
import com.ayoub.currencyconvertor.exception.ExceptionPayload;

public interface Assert {

    static void assertNotNull(Object value, ExceptionPayload exceptionPayload) {
        if (value == null) {
            throw new BusinessException(exceptionPayload);
        }
    }

}
