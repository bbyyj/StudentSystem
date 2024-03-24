package com.example.backend.controller;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signupAdmin")//需要注册/导入新账号吗？
    public JwtAuthenticationResponse signup(@RequestBody AdminSignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signinAdmin")
    public JwtAuthenticationResponse signin(@RequestBody AdminSigninRequest request) {
        return authenticationService.signin(request);//string token
    }
}
