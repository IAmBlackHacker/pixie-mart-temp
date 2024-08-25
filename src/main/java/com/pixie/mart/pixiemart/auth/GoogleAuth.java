package com.pixie.mart.pixiemart.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.pixie.mart.pixiemart.exceptions.InvalidDataException;
import com.pixie.mart.pixiemart.models.collections.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class GoogleAuth {

    @Value("${google.oauth.client}")
    private String googleOauthClient;

    private GoogleIdTokenVerifier verifier;

    @PostConstruct
    private void setupVerifier() {
        verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleOauthClient)).build();
    }

    public User getUserFromToken(String oauthToken) throws GeneralSecurityException, IOException, InvalidDataException {
        GoogleIdToken googleIdToken = verifier.verify(oauthToken);
        if (googleIdToken != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            return User.builder().username(payload.getEmail())
                    .firstName((String) payload.getOrDefault("given_name", ""))
                    .lastName((String) payload.getOrDefault("family_name", "")).enabled(payload.getEmailVerified())
                    .profileUrl((String) payload.getOrDefault("picture", "")).build();
        } else {
            throw new InvalidDataException("Invalid oauth token", oauthToken);
        }
    }
}
