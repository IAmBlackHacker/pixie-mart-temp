package com.pixie.mart.pixiemart.utils.validators;

import com.pixie.mart.pixiemart.models.UserKind;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanValidator {
    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isEmpty(String object) {
        return isNull(object) || object.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> object) {
        return isNull(object) || object.isEmpty();
    }

    public static boolean isInvalidSeller(UserAuthRequest userAuthRequest) {
        return userAuthRequest.getAuthorities().stream()
                .noneMatch(authorities -> UserKind.SELLER.equals(authorities.getAuthority())
                        || UserKind.SITE_ADMIN.equals(authorities.getAuthority()));
    }

    public static boolean isInvalidInspector(UserAuthRequest userAuthRequest) {
        return userAuthRequest.getAuthorities().stream()
                .noneMatch(authorities -> UserKind.INSPECTOR.equals(authorities.getAuthority())
                        || UserKind.SITE_ADMIN.equals(authorities.getAuthority()));
    }

    public static boolean isInvalidApproval(UserAuthRequest userAuthRequest) {
        return userAuthRequest.getAuthorities().stream()
                .noneMatch(authorities -> UserKind.PAYMENT_APPROVAL.equals(authorities.getAuthority())
                        || UserKind.SITE_ADMIN.equals(authorities.getAuthority()));
    }
}
