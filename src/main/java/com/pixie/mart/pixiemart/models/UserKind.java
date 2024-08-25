package com.pixie.mart.pixiemart.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class UserKind implements GrantedAuthority {
    public static final String SITE_ADMIN = "SITE_ADMIN";
    public static final String SITE_MANAGER = "SITE_MANAGER";
    public static final String SELLER = "SELLER";
    public static final String INSPECTOR = "INSPECTOR";
    public static final String PAYMENT_APPROVAL = "PAYMENT_APPROVAL";
    public static final String NORMAL_USER = "NORMAL_USER";

    @Builder.Default
    private String authority = UserKind.NORMAL_USER;
}
