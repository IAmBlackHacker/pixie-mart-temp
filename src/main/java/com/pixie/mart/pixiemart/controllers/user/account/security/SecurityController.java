package com.pixie.mart.pixiemart.controllers.user.account.security;

import com.pixie.mart.pixiemart.constants.url.UserAccountURLConstant;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import com.pixie.mart.pixiemart.models.responses.PixieResponseInterface;
import com.pixie.mart.pixiemart.models.responses.account.security.SecurityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(UserAccountURLConstant.ACCOUNT_SECURITY_BASE_URL)
public class SecurityController {
    @GetMapping
    public ResponseEntity<PixieResponseInterface<SecurityResponse>> getSecurityParams(
            @AuthenticationPrincipal UserAuthRequest user) {
        return ResponseEntity.ok().body(SecurityResponse.response(user));
    }
}
