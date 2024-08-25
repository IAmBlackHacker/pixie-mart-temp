package com.pixie.mart.pixiemart.exceptions;

import com.pixie.mart.pixiemart.models.responses.PixieResponseInterface;
import com.pixie.mart.pixiemart.models.responses.PixieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandlerAdvice {

    private static final String BAD_REQUEST = "Error - bad request:";

    @ExceptionHandler({ PixieMartException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public <T> PixieResponseInterface<T> pixieMartException(PixieMartException ex) {
        log.error(BAD_REQUEST, ex);
        return PixieResponse.getDefaultFailureResponse(ex.getPixieMartExceptionType().getMessage());
    }

    @ExceptionHandler({ RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public <T> PixieResponseInterface<T> runtimeException(RuntimeException ex) {
        log.error("Error - internal server error:", ex);
        return PixieResponse.getDefaultFailureResponse();
    }

    @ExceptionHandler({ ValidationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public <T> PixieResponseInterface<T> validationException(ValidationException ex) {
        log.error(BAD_REQUEST, ex);
        return PixieResponse.getDefaultFailureResponse("Data Validation Error");
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public <T> PixieResponseInterface<T> validationException(MethodArgumentNotValidException ex) {
        log.error(BAD_REQUEST, ex);
        FieldError fieldError = ex.getFieldError();
        if (fieldError != null) {
            return PixieResponse
                    .getDefaultFailureResponse(fieldError.getDefaultMessage() + " for " + fieldError.getField());
        }
        return PixieResponse.getDefaultFailureResponse("Data Validation Error");
    }
}