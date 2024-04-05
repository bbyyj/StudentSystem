package com.example.backend.service;

import com.example.backend.dao.request.*;
import com.example.backend.dao.response.AdminInformationResponse;
import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.dao.response.StudentInformationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AuthenticationService {
    JwtAuthenticationResponse signupAdmin(AdminSignUpRequest request);

    JwtAuthenticationResponse signinAdmin(AdminSigninRequest request);

    JwtAuthenticationResponse signupStudent(StudentSignUpRequest request);

    JwtAuthenticationResponse signinStudent(StudentSigninRequest request);

    AdminInformationResponse getAdminInfomation(String netId);

    StudentInformationResponse getStudentInfomation(String netId);

    ResponseEntity<String> uploadExcelFile(MultipartFile file);

}
