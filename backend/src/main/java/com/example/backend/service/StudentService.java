package com.example.backend.service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    UserDetailsService studentDetailsService();
    ResponseEntity<String> uploadExcelFile(MultipartFile file);
}
