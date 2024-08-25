package com.pixie.mart.pixiemart.controllers.auth;

import com.pixie.mart.pixiemart.auth.GoogleAuth;
import com.pixie.mart.pixiemart.constants.url.URLConstant;
import com.pixie.mart.pixiemart.constants.Constant;
import com.pixie.mart.pixiemart.models.responses.PixieResponseInterface;
import com.pixie.mart.pixiemart.models.collections.user.User;
import com.pixie.mart.pixiemart.models.requests.GoogleAuthToken;
import com.pixie.mart.pixiemart.models.responses.PixieResponse;
import com.pixie.mart.pixiemart.repositories.user.UserRepositoryManager;
import com.pixie.mart.pixiemart.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(URLConstant.AUTH_URL)
public class AuthController {
    @Autowired
    private GoogleAuth googleAuth;

    @Autowired
    private UserRepositoryManager userManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(URLConstant.GOOGLE_OAUTH_URL)
    public <T> ResponseEntity<PixieResponseInterface<T>> authenticateToken(
            @RequestBody GoogleAuthToken googleAuthToken) {
        try {
            User user = googleAuth.getUserFromToken(googleAuthToken.getToken());
            User existingUser = userManager.getUserByUsername(user.getUsername());
            if (existingUser == null) {
                user.setAuthToken(RandomStringUtils.randomAlphanumeric(Constant.PASS_LENGTH));
                user.setPassword(passwordEncoder.encode(user.getAuthToken()));
                user.setEmail(user.getUsername());
                userManager.save(user).block();
            } else {
                user = existingUser;
            }

            doAuthenticateAndGetToken(user);
            String accessToken = Constant.AUTH_PREFIX + jwtTokenUtil.generateAccessToken(user.getId().toHexString());
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, accessToken)
                    .body(PixieResponse.getDefaultSuccessResponse(accessToken));
        } catch (Exception ex) {
            log.error("Google auth issue: ", ex);
            return ResponseEntity.badRequest().body(PixieResponse.getDefaultFailureResponse(ex.getMessage()));
        }
    }

    private void doAuthenticateAndGetToken(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getAuthToken(), user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        jwtTokenUtil.generateAccessToken(((User) authentication.getPrincipal()).getId().toHexString());
    }
}
