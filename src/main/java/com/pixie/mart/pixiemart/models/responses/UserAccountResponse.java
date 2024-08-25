package com.pixie.mart.pixiemart.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import com.pixie.mart.pixiemart.utils.validators.BooleanValidator;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountResponse {
    private AccountAccess seller;
    private AccountAccess inspector;
    private AccountAccess approvals;

    public static PixieResponseInterface<UserAccountResponse> response(UserAuthRequest userAuthRequest) {
        return PixieResponse.getSuccessResponseWithBody(UserAccountResponse.builder()
                .seller(AccountAccess.builder().enabled(!BooleanValidator.isInvalidSeller(userAuthRequest)).build())
                .inspector(
                        AccountAccess.builder().enabled(!BooleanValidator.isInvalidInspector(userAuthRequest)).build())
                .approvals(
                        AccountAccess.builder().enabled(!BooleanValidator.isInvalidApproval(userAuthRequest)).build())
                .build());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class AccountAccess {
        private Boolean enabled = false;
    }
}
