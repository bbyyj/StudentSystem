package com.example.backend.controller;

import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.service.StudentService;

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
@RequestMapping("/student/materialUpload")
@RequiredArgsConstructor
public class AuthenticationController {

    private final MaterialUploadService materialUploadService;

    @PostMapping("/part1")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart1(request);
    }

    @PostMapping("/part2")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart2(request);
    }

    @PostMapping("/part3")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart3(request);
    }

    @PostMapping("/part4")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart4(request);
    }

    @PostMapping("/part5")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart5(request);
    }

    @PostMapping("/part6")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart6(request);
    }

    @PostMapping("/part7")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return MaterialUploadServiceService.uploadPart7(request);
    }

}
