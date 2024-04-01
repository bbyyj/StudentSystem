package com.example.backend.service;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.request.StudentSignUpRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signupAdmin(AdminSignUpRequest request);

    JwtAuthenticationResponse signinAdmin(AdminSigninRequest request);

    JwtAuthenticationResponse signupStudent(StudentSignUpRequest request);

    JwtAuthenticationResponse signinStudent(StudentSigninRequest request);
}
