package com.example.backend.controller;

import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.service.RuleReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ruleReview")
public class RuleReviewController {
    private final RuleReviewService ruleReviewService;

    @PostMapping("/addReview")
    public ResponseEntity<String> addRuleType(@RequestBody ReviewAddRequest request){
        return ruleReviewService.addReview(request);
    }




}