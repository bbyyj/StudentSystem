package com.example.backend.service;

import com.example.backend.dao.request.ReviewAddRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface RuleReviewService {
    @Transactional
    ResponseEntity<String>  addReview(ReviewAddRequest request);


}
