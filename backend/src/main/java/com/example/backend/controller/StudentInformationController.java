package com.example.backend.controller;

import com.example.backend.dao.request.MyInfoModifyRequest;
import com.example.backend.dao.request.StuInfoModifyRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.CombinedCA;
import com.example.backend.dao.response.StudentListResponse;
import com.example.backend.entities.Student;
import com.example.backend.service.StuInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.List;

// 学生个人信息
@RestController
@RequiredArgsConstructor
public class StudentInformationController {

    private final StuInfoService stuInfoService;

//    @GetMapping("/test/findByPage")
//    public Page<Student> getStudentsByPage(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return stuInfoService.findByPage(page, size);
//    }

    @GetMapping("/topAdmin/getAllStudent")//返回所有学生的全部信息
    public Page<Student> getAllStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        return stuInfoService.findByPage(page, size);
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

    @PutMapping("/headTeacher/modifyStuInfoExcel")//老师批量导入学生信息excel
    public ResponseEntity<String> modifyStuInfoExcel(@RequestParam("file") MultipartFile file) {
        return stuInfoService.modifyStuInfoExcel(file);
    }

    @DeleteMapping("/headTeacher/deleteStuInfo")//老师删除学生的信息
    public ResponseEntity<String> deleteStuInfo(@RequestParam String netId) {
        return stuInfoService.deleteStuInfo(netId);
    }

    @GetMapping("/headTeacher/searchStuInfo")//老师查询学生的信息
    public Page<Student> searchStuInfo(@RequestParam String keyname, @RequestParam String keyword,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "8") int size) {
        return stuInfoService.searchStuInfo(keyname, keyword, page, size);
    }

    @GetMapping("/headTeacher/getClassStudent")//返回班级学生的全部信息
    public Page<Student> getClassStudent(@RequestParam Boolean isUndergraduate,
                                             @RequestParam Year admissionYear,
                                             @RequestParam Integer classId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "8") int size) {
        return stuInfoService.getClassStudent(isUndergraduate, admissionYear, classId, page, size);
    }

    @GetMapping("/student/getMyCA")//返回单个学生的全部综测
    public List<CombinedCA> getMyCA(@RequestParam String sid,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return stuInfoService.getMyCA(sid, page, size);
    }
}
