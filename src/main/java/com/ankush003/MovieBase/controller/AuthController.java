package com.ankush003.MovieBase.controller;

import com.ankush003.MovieBase.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(Authentication authentication) {
        log.info("Logging in user");
        log.info("Authentication: " + authentication.getName());
        return ResponseEntity.ok(authService.getJwtTokenAfterAuthentication(authentication));
    }

}
