package com.pixie.mart.pixiemart.constants.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLConstant {
    public static final String BASE_API_URL = "/api";
    public static final String VERSION_URL = "/version";
    public static final String USER_INFO_URL = BASE_API_URL + "/user";
    public static final String UNIVERSAL_SEARCH_URL = BASE_API_URL + "/search";
    public static final String ITEM_URL = BASE_API_URL + "/item";

    public static final String AUTH_URL = "/auth";
    public static final String GOOGLE_OAUTH_URL = "/authenticate/token/google_oauth2";

    public static final String BRANDS_BASE_URL = BASE_API_URL + "/bicycle/brands";
    public static final String SELL_ITEMS_BASE_URL = BASE_API_URL + "/sell";
    public static final String TONGUE_TWISTER_URL = BASE_API_URL + "/pixie/twister";
}
