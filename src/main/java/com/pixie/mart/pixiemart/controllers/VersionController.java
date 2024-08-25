package com.pixie.mart.pixiemart.controllers;

import com.pixie.mart.pixiemart.constants.url.URLConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VersionController {
    @Value("${pixie-mart.pom.version}")
    String pomVersion;

    @GetMapping(URLConstant.VERSION_URL)
    public ResponseEntity<String> pomVersion() {
        return ResponseEntity.ok().body(pomVersion);
    }

    @GetMapping(URLConstant.BASE_API_URL)
    public ResponseEntity<String> hiResponse() {
        return ResponseEntity.ok().body("Hi from backend!");
    }
}
