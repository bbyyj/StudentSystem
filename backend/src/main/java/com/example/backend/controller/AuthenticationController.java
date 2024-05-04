package com.example.backend.controller;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.request.StudentSignUpRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.AdminInformationResponse;
import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.dao.response.StudentInformationResponse;
import com.example.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


// 注册登录
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signupAdmin")//单个老师注册
    public JwtAuthenticationResponse signupAdmin(@RequestBody AdminSignUpRequest request) {
        return authenticationService.signupAdmin(request);
    }

    @PostMapping("/signinAdmin")//老师登录
    public JwtAuthenticationResponse signinAdmin(@RequestBody AdminSigninRequest request) {
        return authenticationService.signinAdmin(request);//string token
    }

    @GetMapping("/getAdminInfo")//根据net_id返回老师信息
    public AdminInformationResponse getAdminInfomation(@RequestParam String netId) {
        return authenticationService.getAdminInfomation(netId);
    }


    @PostMapping("/signupStudent")//单个学生注册
    public JwtAuthenticationResponse signupStudent(@RequestBody StudentSignUpRequest request) {
        return authenticationService.signupStudent(request);
    }

    @PostMapping("/signupStudentExcel")//从excel批量导入学生注册
    public ResponseEntity<String> signupStudentExcel(@RequestParam("file") MultipartFile file) {
        return authenticationService.signupStudentExcel(file);
    }

    @PostMapping("/signinStudent")//学生登录
    public JwtAuthenticationResponse signinStudent(@RequestBody StudentSigninRequest request) {
        return authenticationService.signinStudent(request);//string token
    }

    @GetMapping("/getStudentInfo")//根据net_id返回学生信息
    public StudentInformationResponse getStudentfomation(@RequestParam String netId) {
        return authenticationService.getStudentInfomation(netId);
    }

}
