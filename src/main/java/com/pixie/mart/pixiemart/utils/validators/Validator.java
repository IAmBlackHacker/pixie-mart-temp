package com.pixie.mart.pixiemart.utils.validators;

import com.pixie.mart.pixiemart.constants.Constant;
import com.pixie.mart.pixiemart.exceptions.InvalidDataException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

    public static boolean isValidJwtToken(String authHeader) {
        return authHeader != null && authHeader.startsWith(Constant.AUTH_PREFIX);
    }

    public static <T> void validateIsNotNull(T object, String exceptionMessage) {
        if (object == null) {
            throw new InvalidDataException(exceptionMessage, object);
        }
    }

    public static void isValidUsername(String username) {
        validateIsNotNull(username, "Invalid username");
    }

    public static void validateIsNotEmpty(String string, String exceptionMessage) {
        validateIsNotNull(string, exceptionMessage);
        if (string.isEmpty() || string.isBlank()) {
            throw new InvalidDataException(exceptionMessage, string);
        }
    }
}
