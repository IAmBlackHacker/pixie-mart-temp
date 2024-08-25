package com.pixie.mart.pixiemart.exceptions;

import com.pixie.mart.pixiemart.constants.enums.PixieMartExceptionType;

public class InvalidImageException extends PixieMartException {
    public InvalidImageException() {
        super(PixieMartExceptionType.INVALID_OBJECT_EXCEPTION);
    }
}
