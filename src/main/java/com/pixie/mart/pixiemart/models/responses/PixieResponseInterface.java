package com.pixie.mart.pixiemart.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pixie.mart.pixiemart.constants.enums.ResponseStatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface PixieResponseInterface<T> {
    ResponseStatusEnum getStatus();

    String getMessage();

    String getErrorCode();

    T getBody();
}