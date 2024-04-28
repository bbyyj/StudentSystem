package com.example.backend.service.impl;

import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.entities.Review;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.service.RuleReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class RuleReviewServiceImpl implements RuleReviewService {
    private final ReviewRepository  reviewRepository;
    public ResponseEntity<String> addReview(ReviewAddRequest request) {
        try {
            Review review = new Review();
            review.setName(request.getName());
            review.setStart_time(Timestamp.valueOf(request.getStart_time()));
            review.setEnd_time(Timestamp.valueOf(request.getEnd_time()));
            reviewRepository.save(review);
            return ResponseEntity.ok("Review added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding Review.");
        }
    }
}
