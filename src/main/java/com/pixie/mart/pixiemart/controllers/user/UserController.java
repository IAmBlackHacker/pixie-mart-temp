package com.pixie.mart.pixiemart.controllers.user;

import com.pixie.mart.pixiemart.constants.url.URLConstant;
import com.pixie.mart.pixiemart.exceptions.UnAuthorizedException;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import com.pixie.mart.pixiemart.models.responses.PixieResponseInterface;
import com.pixie.mart.pixiemart.models.responses.UserAccountResponse;
import com.pixie.mart.pixiemart.models.responses.UserResponse;
import com.pixie.mart.pixiemart.utils.validators.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(URLConstant.USER_INFO_URL)
public class UserController {

    @GetMapping
    public ResponseEntity<PixieResponseInterface<UserResponse>> getUser(@AuthenticationPrincipal UserAuthRequest user) {
        if (user == null) {
            throw new UnAuthorizedException("User unauthorized to perform this action", user);
        }

        Validator.isValidUsername(user.getUsername());
        return ResponseEntity.ok().body(UserResponse.response(user));
    }

    @GetMapping("/account")
    public ResponseEntity<PixieResponseInterface<UserAccountResponse>> getUserAccount(
            @AuthenticationPrincipal UserAuthRequest user) {
        Validator.isValidUsername(user.getUsername());
        return ResponseEntity.ok().body(UserAccountResponse.response(user));
    }
}
