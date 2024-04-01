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
@RequiredArgsConstructor
public class AuthenticationController {

    private final StudentService studentService;

    @PostMapping("/topAdmin/importDormitory")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return studentServiceService.importDormitory(request);
    }

    @GetMapping("/topAdmin/getAllStudentInformation")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return studentServiceService.importDormitory(request);
    }

    @GetMapping("/headTeacher/getClassStudentInformation")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return studentServiceService.importDormitory(request);
    }

    @PostMapping("/student/modifyInformation")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return studentServiceService.modifyInformation(request);
    }

    @GetMapping("/student/getMyInformation")
    public JwtAuthenticationResponse signin(@RequestBody StudentSigninRequest request) {
        return studentServiceService.importDormitory(request);
    }

}
