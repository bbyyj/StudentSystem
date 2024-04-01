package com.example.backend.controller;

import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.dao.response.StudentListResponse;
import com.example.backend.service.StudentService;
import com.example.backend.entities.Student;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class StudentInformationController {

    private final StudentService studentService;

//    @PostMapping("/topAdmin/importDormitory")
//    public JwtAuthenticationResponse importDormitory(@RequestBody StudentSigninRequest request) {
//        return studentServiceService.importDormitory(request);
//    }

    @GetMapping("/topAdmin/getAllStudent")
    public StudentListResponse getAllStudent() {
        System.out.println("-----------------------place 0------------------");
        List<Student> students = studentService.getAllStudent();
        System.out.println("-----------------------place 1------------------");
        return new StudentListResponse(students, students.size());
    }


//    @GetMapping("/headTeacher/getClassStudent")
//    public JwtAuthenticationResponse getClassStudent(@RequestBody StudentSigninRequest request) {
//        return studentServiceService.getClassStudent(request);
//    }
//
//    @PostMapping("/student/modifyStudent")
//    public JwtAuthenticationResponse modifyStudent(@RequestBody StudentSigninRequest request) {
//        return studentServiceService.modifyStudent(request);
//    }
//
//    @GetMapping("/student/getMyInformation")
//    public JwtAuthenticationResponse getMyInformation(@RequestBody StudentSigninRequest request) {
//        return studentServiceService.getMyInformation(request);
//    }

}
