package com.example.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.entities.Review;
import com.example.backend.service.RuleReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




}