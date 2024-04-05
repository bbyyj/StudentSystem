package com.example.backend.controller;

import com.example.backend.dao.request.MyInfoModifyRequest;
import com.example.backend.dao.request.StuInfoModifyRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.StudentListResponse;
import com.example.backend.entities.Student;
import com.example.backend.service.StuInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.List;

//学生个人信息
@RestController
@RequiredArgsConstructor
public class StudentInformationController {

    private final StuInfoService stuInfoService;

    @GetMapping("/topAdmin/getAllStudent")//返回所有学生的全部信息
    public StudentListResponse getAllStudent() {
        List<Student> students = stuInfoService.getAllStudent();
        return new StudentListResponse(students, students.size());
    }

    @PutMapping("/topAdmin/importStudentDormitory")//批量导入宿舍表excel
    public ResponseEntity<String> importStudentDormitory(@RequestParam("file") MultipartFile file) {
        return stuInfoService.importStudentDormitory(file);
    }


    @PutMapping("/student/modifyMyInfo")//学生修改自己的信息
    public ResponseEntity<String> modifyMyInfo(@RequestBody MyInfoModifyRequest request) {
        return stuInfoService.modifyMyInfo(request);
    }

    @PutMapping("/student/modifyPassword")//学生修改自己的密码
    public ResponseEntity<String> modifyPassword(@RequestBody StudentSigninRequest request) {
        return stuInfoService.modifyPassword(request);
    }

    @PutMapping("/headTeacher/modifyStuInfo")//老师修改学生的信息
    public ResponseEntity<String> modifyStuInfo(@RequestBody StuInfoModifyRequest request) {
        return stuInfoService.modifyStuInfo(request);
    }

    @DeleteMapping("/headTeacher/deleteStuInfo")//老师删除学生的信息
    public ResponseEntity<String> deleteStuInfo(@RequestParam String netId) {
        return stuInfoService.deleteStuInfo(netId);
    }

    @GetMapping("/headTeacher/searchStuInfo")//老师查询学生的信息
    public StudentListResponse searchStuInfo(@RequestParam String keyword) {
        List<Student> students = stuInfoService.searchStuInfo(keyword);
        return new StudentListResponse(students, students.size());
    }

    @GetMapping("/headTeacher/getClassStudent")//返回所有学生的全部信息
    public StudentListResponse getAllStudent(@RequestParam Boolean isUndergraduate, @RequestParam Year admissionYear, @RequestParam Integer classId) {
        List<Student> students = stuInfoService.getClassStudent(isUndergraduate, admissionYear, classId);
        return new StudentListResponse(students, students.size());
    }
}
