package com.pixie.mart.pixiemart.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong";
    public static final int PASS_LENGTH = 21;
    public static final int USER_ID_APPENDER_LEN = 17;
    public static final String AUTH_PREFIX = "Bearer ";
    public static final String DEFAULT_BRAND = "OTHER_BRAND";
    public static final String DEFAULT_MODEL = "OTHER_MODEL";

    public static final int ITEM_MAX_IMAGE_LIMIT = 10;
    public static final String USER_AGENT = "";
}
