package com.example.backend.controller;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.request.StudentSignUpRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.service.AuthenticationService;
//import com.example.backend.service.StudentService;

import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService studentService;
    @PostMapping("/signupAdmin")//单个老师注册
    public JwtAuthenticationResponse signupAdmin(@RequestBody AdminSignUpRequest request) {
        return authenticationService.signupAdmin(request);
    }

    @PostMapping("/signinAdmin")
    public JwtAuthenticationResponse signinAdmin(@RequestBody AdminSigninRequest request) {
        return authenticationService.signinAdmin(request);//string token
    }

    @PostMapping("/signupStudent")//单个学生注册
    public JwtAuthenticationResponse signupStudent(@RequestBody StudentSignUpRequest request) {
        return authenticationService.signupStudent(request);
    }

    @PostMapping("/signupStudentExcel")//从excel批量导入学生注册
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        return studentService.uploadExcelFile(file);
    }

    @PostMapping("/signinStudent")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return authenticationService.signinStudent(request);
    }

}
