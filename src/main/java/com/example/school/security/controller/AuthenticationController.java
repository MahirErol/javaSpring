package com.example.school.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.security.dao.request.SignUpRequest;
import com.example.school.security.dao.request.SigninRequest;
import com.example.school.security.dao.response.JwtAuthenticationResponse;
import com.example.school.security.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/signupadmin")
    public ResponseEntity<JwtAuthenticationResponse> signupAdmin(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signupAdmin(request));
    }

    @PostMapping("/signinadmin")
    public ResponseEntity<JwtAuthenticationResponse> signinAdmin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signinAdmin(request));
    }
}
