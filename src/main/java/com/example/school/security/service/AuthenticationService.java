package com.example.school.security.service;

import com.example.school.security.dao.request.SignUpRequest;
import com.example.school.security.dao.request.SigninRequest;
import com.example.school.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse signupAdmin(SignUpRequest request);

    JwtAuthenticationResponse signinAdmin(SigninRequest request);
}
