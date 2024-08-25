package com.pixie.mart.pixiemart.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum PixieMartExceptionType {
    INVALID_OBJECT_EXCEPTION("CODE_01"), INVALID_DATA_EXCEPTION("CODE_02"), UNAUTHORIZED_ACCESS("CODE_03");

    private final String code;

    @Setter
    private String message;

    @Setter
    private Object data;
}
