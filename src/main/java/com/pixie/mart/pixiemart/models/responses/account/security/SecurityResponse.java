package com.pixie.mart.pixiemart.models.responses.account.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import com.pixie.mart.pixiemart.models.responses.PixieResponse;
import com.pixie.mart.pixiemart.models.responses.PixieResponseInterface;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityResponse {
    private String firstName;

    private String lastName;
    private String gender;
    private String email;
    private String mobile;

    public static PixieResponseInterface<SecurityResponse> response(UserAuthRequest user) {
        return PixieResponse.getSuccessResponseWithBody(
                SecurityResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                        .gender(user.getGender()).email(user.getEmail()).mobile(user.getMobileNumber()).build());
    }
}
