package com.example.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.entities.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RuleReviewService {
    @Transactional
    JSONObject addReview(ReviewAddRequest request);
    @Transactional
    List<Review> getReviews();


}
