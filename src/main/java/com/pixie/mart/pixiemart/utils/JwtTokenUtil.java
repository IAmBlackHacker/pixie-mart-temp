package com.pixie.mart.pixiemart.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pixie.mart.pixiemart.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtil {
    @Value("${spring.jwt.token}")
    private String jwtSecret;

    @Value("${spring.jwt.issuer}")
    private String issuer;

    @Value("${spring.jwt.expiration}")
    private long expiration;

    public String generateAccessToken(String userId) {
        String securedUID = userId + RandomStringUtils.randomAlphanumeric(Constant.USER_ID_APPENDER_LEN);
        return JWT.create().withSubject(securedUID).withIssuer(issuer).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(Algorithm.HMAC512(jwtSecret));
    }

    public String getUserId(String token) {
        String secureUID = JWT.require(Algorithm.HMAC512(jwtSecret)).build().verify(token).getSubject();
        return secureUID.substring(0, secureUID.length() - Constant.USER_ID_APPENDER_LEN);
    }

    public Date getExpirationDate(String token) {
        return JWT.require(Algorithm.HMAC512(jwtSecret)).build().verify(token).getExpiresAt();
    }

    public boolean validate(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecret)).build().verify(token);
            return true;
        } catch (Exception ex) {
            log.error("Invalid JWT signature: ", ex);
        }
        return false;
    }
}
