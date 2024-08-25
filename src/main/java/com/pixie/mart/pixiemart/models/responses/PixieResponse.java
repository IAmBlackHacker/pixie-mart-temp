package com.pixie.mart.pixiemart.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pixie.mart.pixiemart.constants.Constant;
import com.pixie.mart.pixiemart.constants.enums.ResponseStatusEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PixieResponse<T> implements PixieResponseInterface<T> {
    @Builder.Default
    private ResponseStatusEnum status = ResponseStatusEnum.SUCCESS;
    private String message;
    private String errorCode;
    private T body;

    public static <T> PixieResponseInterface<T> getDefaultFailureResponse() {
        return getDefaultFailureResponse(Constant.DEFAULT_ERROR_MESSAGE);
    }

    public static <T> PixieResponseInterface<T> getDefaultFailureResponse(String message) {
        return PixieResponse.<T> builder().status(ResponseStatusEnum.FAILURE).message(message)
                .errorCode(UUID.randomUUID().toString()).build();
    }

    public static <T> PixieResponseInterface<T> getDefaultSuccessResponse(String message) {
        return PixieResponse.<T> builder().status(ResponseStatusEnum.SUCCESS).message(message).build();
    }

    public static <T> PixieResponseInterface<T> getSuccessResponseWithBody(T body) {
        return PixieResponse.<T> builder().status(ResponseStatusEnum.SUCCESS).body(body).build();
    }

}