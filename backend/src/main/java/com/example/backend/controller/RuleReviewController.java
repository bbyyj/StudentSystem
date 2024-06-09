package com.example.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.dao.request.StudentReviewListRequest;
import com.example.backend.entities.Review;
import com.example.backend.entities.StudentReviewList;
//import com.example.backend.entities.StudentReviewListDTO;
import com.example.backend.service.RuleReviewService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.Year;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ruleReview")
public class RuleReviewController {
    private final RuleReviewService ruleReviewService;


    @PostMapping("/addReview")
    public JSONObject addReview(@RequestBody ReviewAddRequest request){
        return ruleReviewService.addReview(request);
    }
    @PutMapping("/updateReview")
    public ResponseEntity<String> updateReview(@RequestBody ReviewAddRequest request){
        return ruleReviewService.updateReview(request);
    }
    @GetMapping("/getAllReview")
    public Page<Review> getAllReview(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "8") int size){
        return ruleReviewService.getReviews(page, size);
    }
    @GetMapping("/getAllReviewByCondition")
    public Page<Review> getAllReviewByCondition(@RequestParam String name,@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "8") int size){
        return ruleReviewService.getReviewsByCondition(name,page, size);
    }
    @GetMapping("/getStudentReviewList")
    public Page<StudentReviewList> getStudentReviewList(@RequestParam int id, @RequestParam boolean isUndergraduate, @RequestParam Year admission_year,@RequestParam(defaultValue = "0") int classId,@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "8") int size){
        StudentReviewListRequest request = new StudentReviewListRequest();
        request.setId(id);
        request.setIsUndergraduate(isUndergraduate);
        request.setAdmission_year(admission_year);
        request.setClassId(classId);
        return ruleReviewService.getStudentReviewList(request,page, size);
    }
    @GetMapping("/getStudentReviewListByCondition")
    public Page<StudentReviewList> getStudentReviewListByCondition(@RequestParam int id, @RequestParam boolean isUndergraduate, @RequestParam Year admission_year,@RequestParam(defaultValue = "0") int classId,@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "8") int size,@RequestParam String state,@RequestParam String sid,@RequestParam String sname){
        StudentReviewListRequest request = new StudentReviewListRequest();
        request.setId(id);
        request.setIsUndergraduate(isUndergraduate);
        request.setAdmission_year(admission_year);
        request.setClassId(classId);
        return ruleReviewService.getStudentReviewListByCondition(request,page, size,state,sid,sname);
    }
    @GetMapping("/getStudentMatiarial")
    public Page<Map<String, Object>> getStudentMatiarial(@RequestParam int id, @RequestParam String sid,@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "8") int size){
        System.out.println(id);
        System.out.println(sid);
        return ruleReviewService.getStudentMatiarial(id,sid,page,size);
    }
    @PostMapping("/submitReview")
    public ResponseEntity<String>  submitReview(@RequestBody List<Map<String, Object>> data){
        return ruleReviewService.submitReview(data);
    }

    @GetMapping("/getAllStudentReviewList")
    public Page<StudentReviewList> getAllStudentReviewList(@RequestParam int id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size){

        return ruleReviewService.getAllStudentReviewList(id,page, size);
    }

    @GetMapping("/getStudentReviewListByTeacher")
    public Page<StudentReviewList> getStudentReviewListByTeacher(@RequestParam int id, @RequestParam(defaultValue = "1") boolean isUndergraduate, @RequestParam(defaultValue = "2021") Year admission_year,@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "8") int size){

        return ruleReviewService.getStudentReviewListByTeacher(id,isUndergraduate,admission_year,page, size);
    }
    @PutMapping("/upLoadExamScore")//老师批量导入学生信息excel
    public ResponseEntity<String> upLoadExamScore(@RequestParam int id, @RequestParam("file") MultipartFile file) {
        return ruleReviewService.upLoadExamScore(id,file);
    }




}