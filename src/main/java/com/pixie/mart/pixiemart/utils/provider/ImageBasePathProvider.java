package com.pixie.mart.pixiemart.utils.provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageBasePathProvider {
    public static String getUserBasePath(String userId, String orderId) {
        return Paths.get("users", userId, "orders", orderId).toString();
    }

    public static String getBicycleModelsPath(String modelId) {
        return Paths.get("items", "bicycles", modelId).toString();
    }
}
