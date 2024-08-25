package com.pixie.mart.pixiemart.interfaces;

import com.pixie.mart.pixiemart.constants.enums.OrderTypeEnum;
import com.pixie.mart.pixiemart.constants.enums.SellBicycleState;
import com.pixie.mart.pixiemart.models.collections.Address;
import org.bson.types.ObjectId;

import java.util.List;

public interface OrderInterface {
    ObjectId getId();

    String getBrand();

    String getModel();

    String getDescription();

    String getFrameSize();

    String getColor();

    String getBikeType();

    String getPreferredTimeSlot();

    List<String> getImages();

    OrderTypeEnum getOrderType();

    SellBicycleState getState();

    String getLatLong();

    Address getAddress();

    List<String> getEvents();

    String getCreatedBy();
}
