package com.pixie.mart.pixiemart.exceptions;

import com.pixie.mart.pixiemart.constants.enums.PixieMartExceptionType;

public class UnAuthorizedException extends PixieMartException {
    public <T> UnAuthorizedException(String message, T data) {
        super(PixieMartExceptionType.UNAUTHORIZED_ACCESS);
        getPixieMartExceptionType().setMessage(message);
        getPixieMartExceptionType().setData(data);
    }
}
