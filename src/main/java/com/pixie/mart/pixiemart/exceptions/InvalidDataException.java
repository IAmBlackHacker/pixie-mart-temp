package com.pixie.mart.pixiemart.exceptions;

import com.pixie.mart.pixiemart.constants.enums.PixieMartExceptionType;

public class InvalidDataException extends PixieMartException {
    public <T> InvalidDataException(String message, T data) {
        super(PixieMartExceptionType.INVALID_OBJECT_EXCEPTION);
        getPixieMartExceptionType().setMessage(message);
        getPixieMartExceptionType().setData(data);
    }
}
