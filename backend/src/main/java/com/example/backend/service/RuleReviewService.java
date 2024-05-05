package com.example.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.dao.request.StudentReviewListRequest;
import com.example.backend.entities.Review;
import com.example.backend.entities.StudentReviewList;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RuleReviewService {
    @Transactional
    JSONObject addReview(ReviewAddRequest request);
    @Transactional
    List<Review> getReviews();
    @Transactional
    List<StudentReviewList> getStudentReviewList(StudentReviewListRequest request);



}