package com.pixie.mart.pixiemart.constants.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountURLConstant {
    public static final String ACCOUNT_URL = URLConstant.BASE_API_URL + "/user/account";

    public static final String ACCOUNT_ADDRESS_BASE_URL = ACCOUNT_URL + "/address";
    public static final String ACCOUNT_ORDERS_BASE_URL = ACCOUNT_URL + "/orders";
    public static final String ACCOUNT_SECURITY_BASE_URL = ACCOUNT_URL + "/security";
}
