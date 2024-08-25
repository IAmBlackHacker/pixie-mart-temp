package com.pixie.mart.pixiemart.constants.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApprovalsURLConstant {
    public static final String APPROVAL_BASE_URL = UserAccountURLConstant.ACCOUNT_URL + "/approval/cycle";
}
