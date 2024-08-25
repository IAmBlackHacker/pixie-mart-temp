package com.pixie.mart.pixiemart.models.requests;

import com.pixie.mart.pixiemart.models.UserKind;
import com.pixie.mart.pixiemart.models.collections.user.OrderType;
import com.pixie.mart.pixiemart.models.collections.user.User;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class UserAuthRequest {
    private final String id;

    private final String username;

    private final String firstName;
    private final String lastName;

    private final String email;
    private final String gender;

    private final String mobileNumber;

    private final boolean enabled;
    private final boolean credentialsNonExpired;

    private final Set<UserKind> authorities;

    private final List<String> address;

    private final List<OrderType> orders;

    public UserAuthRequest(User user) {
        this.id = user.getId().toHexString();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.mobileNumber = user.getMobileNumber();
        this.enabled = user.isEnabled();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.authorities = user.getAuthorities();
        this.address = user.getAddress();
        this.orders = user.getOrders();
    }
}
