package com.example.backend.service.impl;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.entities.AdminRole;
import com.example.backend.entities.Admin;
import com.example.backend.repository.AdminRepository;
import com.example.backend.service.AuthenticationService;
import com.example.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(AdminSignUpRequest request) {
        var admin = Admin.builder()
                .netId(request.getNetId())
                .password(passwordEncoder.encode(request.getPassword()))
                .adminRole(request.getAdminRole())
                .isUndergraduate(request.isUndergraduate())
                .admissionYear(request.getAdmissionYear())
                .classId(request.getClassId())
                .tid(request.getTid())
                .name(request.getName())
                .build();
        adminRepository.save(admin);
        var jwt = jwtService.generateToken(admin);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(AdminSigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNetId(), request.getPassword()));
        var admin = adminRepository.findByNetId(request.getNetId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid netId or password."));
        var jwt = jwtService.generateToken(admin);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
