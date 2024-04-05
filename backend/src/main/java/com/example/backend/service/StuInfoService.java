package com.example.backend.service;

import com.example.backend.dao.request.MyInfoModifyRequest;
import com.example.backend.dao.request.StuInfoModifyRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.entities.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.List;

public interface StuInfoService {

    List<Student> getAllStudent();

    List<Student> getClassStudent(Boolean isUndergraduate, Year admissionYear, Integer classId);

    @Transactional
    ResponseEntity<String> importStudentDormitory(MultipartFile file);

    @Transactional
    ResponseEntity<String> modifyMyInfo(MyInfoModifyRequest request);

    @Transactional
    ResponseEntity<String> modifyStuInfo(StuInfoModifyRequest request);

    @Transactional
    ResponseEntity<String> modifyPassword(StudentSigninRequest request);

    @Transactional
    ResponseEntity<String> deleteStuInfo(String netId);

    List<Student>  searchStuInfo(String netId);
}
