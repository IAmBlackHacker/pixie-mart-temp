package com.pixie.mart.pixiemart.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String username;
    private String firstName;
    private String lastName;

    public static PixieResponseInterface<UserResponse> response(UserAuthRequest user) {
        return PixieResponse.getSuccessResponseWithBody(UserResponse.builder().username(user.getUsername())
                .firstName(user.getFirstName()).lastName(user.getLastName()).build());
    }
}
