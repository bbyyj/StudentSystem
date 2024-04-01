package com.example.backend.service;

import com.example.backend.entities.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    ResponseEntity<String> uploadExcelFile(MultipartFile file);

    List<Student> getAllStudent();
}
