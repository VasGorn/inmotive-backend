package com.vesmer.inmotive.controller;

import com.vesmer.inmotive.dto.RegisterRequest;
import com.vesmer.inmotive.service.AuthService;
import com.vesmer.inmotive.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @GetMapping("/activate/{username}")
    public ResponseEntity<String> activateAccount(@PathVariable String username) {
        authService.enableAccount(username);
        return new ResponseEntity<>("Account Activation Successfully", OK);
    }
}
