package com.pixie.mart.pixiemart.models;

import com.pixie.mart.pixiemart.constants.enums.PermissionLevel;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPermission implements Serializable {
    private String userId;
    private PermissionLevel permissionLevel;
}
