package com.pixie.mart.pixiemart.constants.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerURLConstant {
    private static final String SELLER_URL = URLConstant.BASE_API_URL + "/seller/cycle";
    public static final String MANAGE_BRANDS_BASE_URL = SELLER_URL + "/brands";
    public static final String MANAGE_ITEMS_BASE_URL = SELLER_URL + "/items";

    public static final String MANAGE_AMAZON_BICYCLE_URL = SELLER_URL + "/amazon/bicycle";
}
