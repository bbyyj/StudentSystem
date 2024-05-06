package com.example.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.dao.request.StudentReviewListRequest;
import com.example.backend.entities.Review;
import com.example.backend.entities.StudentReviewList;
import com.example.backend.service.RuleReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ruleReview")
public class RuleReviewController {
    private final RuleReviewService ruleReviewService;


    @PostMapping("/addReview")
    public JSONObject addRuleType(@RequestBody ReviewAddRequest request){
        return ruleReviewService.addReview(request);
    }
    @GetMapping("/getAllReview")
    public List<Review> getAllReview(){
        return ruleReviewService.getReviews();
    }
    @GetMapping("/getStudentReviewList")
    public List<StudentReviewList> getStudentReviewList(@RequestParam int id, @RequestParam boolean isUndergraduate, @RequestParam Year admission_year,@RequestParam(defaultValue = "0") int classId){
        StudentReviewListRequest request = new StudentReviewListRequest();
        request.setId(id);
        request.setIsUndergraduate(isUndergraduate);
        request.setAdmission_year(admission_year);
        request.setClassId(classId);
        return ruleReviewService.getStudentReviewList(request);
    }
    @GetMapping("/getStudentMatiarial")
    public List<Map<String, Object>> getStudentMatiarial(@RequestParam int id, @RequestParam String sid){
        return ruleReviewService.getStudentMatiarial(id,sid);
    }




}