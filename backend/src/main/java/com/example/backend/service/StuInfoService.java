package com.example.backend.service;

import com.example.backend.dao.request.MyInfoModifyRequest;
import com.example.backend.dao.request.StuInfoModifyRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.CombinedCA;
import com.example.backend.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.List;

public interface StuInfoService {

    Page<Student> findByPage(int page, int size);

    List<Student> getAllStudent();

    Page<Student> getClassStudent(Boolean isUndergraduate, Year admissionYear, Integer classId, int page, int size);

    @Transactional
    ResponseEntity<String> modifyMyInfo(MyInfoModifyRequest request);

    @Transactional
    ResponseEntity<String> modifyStuInfo(StuInfoModifyRequest request);

    @Transactional
    ResponseEntity<String> modifyPassword(StudentSigninRequest request);

    @Transactional
    ResponseEntity<String> deleteStuInfo(String netId);

    Page<Student>  searchStuInfo(String keyname, String keyword, int page, int size);

    ResponseEntity<String> modifyStuInfoExcel(MultipartFile file);

    List<CombinedCA> getMyCA(String sid, int page, int size);
}
