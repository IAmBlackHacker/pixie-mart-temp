package com.pixie.mart.pixiemart.models.collections.user;

import com.pixie.mart.pixiemart.constants.enums.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderType implements Serializable {
    private String id;
    private OrderTypeEnum orderTypeValue;
}
