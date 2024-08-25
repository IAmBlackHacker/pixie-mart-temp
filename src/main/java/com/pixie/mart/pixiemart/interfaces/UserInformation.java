package com.pixie.mart.pixiemart.interfaces;

import com.pixie.mart.pixiemart.models.collections.user.OrderType;

import java.util.List;

public interface UserInformation {
    List<String> getAddress();

    List<OrderType> getOrders();
}
