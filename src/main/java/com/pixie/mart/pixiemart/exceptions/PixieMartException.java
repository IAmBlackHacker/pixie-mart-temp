package com.pixie.mart.pixiemart.exceptions;

import com.pixie.mart.pixiemart.constants.enums.PixieMartExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class PixieMartException extends RuntimeException {
    private final PixieMartExceptionType pixieMartExceptionType;
}
