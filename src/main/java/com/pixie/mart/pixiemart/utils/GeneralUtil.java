package com.pixie.mart.pixiemart.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralUtil {

    public static String getRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
