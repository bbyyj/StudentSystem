package com.example.backend.service;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(AdminSignUpRequest request);

    JwtAuthenticationResponse signin(AdminSigninRequest request);
}
