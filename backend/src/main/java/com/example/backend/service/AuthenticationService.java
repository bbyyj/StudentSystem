package com.example.backend.service;

import com.example.backend.dao.request.SignUpRequest;
import com.example.backend.dao.request.SigninRequest;
import com.example.backend.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
