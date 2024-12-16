package com.example.school.security.service.impl;

import com.example.school.security.entities.Role;
import com.example.school.security.repository.UserRepository;
import com.example.school.security.service.AuthenticationService;
import com.example.school.security.service.JwtService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.school.security.dao.request.SignUpRequest;
import com.example.school.security.dao.request.SigninRequest;
import com.example.school.security.dao.response.JwtAuthenticationResponse;
import com.example.school.security.entities.User;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        // Kullanıcının kimlik doğrulamasını yap
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Kullanıcıyı veritabanından al
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));

        // Kullanıcının admin rolüne sahip olup olmadığını kontrol et
        if (user.getRole() == Role.ADMIN) {
            throw new AccessDeniedException("Admin users must use admin login.");
        }

        // JWT token oluştur ve döndür
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }





    @Override
    public JwtAuthenticationResponse signupAdmin(SignUpRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signinAdmin(SigninRequest request) {
        // Kullanıcının kimlik doğrulamasını yap
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Kullanıcıyı veritabanından al
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));

        // Kullanıcının admin rolüne sahip olup olmadığını kontrol et
        if (user.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Access is denied. Admins only.");
        }

        // JWT token oluştur ve döndür
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}