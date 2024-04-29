package com.example.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.entities.Review;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.service.RuleReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RuleReviewServiceImpl implements RuleReviewService {
    private final ReviewRepository  reviewRepository;
    public JSONObject addReview(ReviewAddRequest request) {
        Review review = new Review();
        review.setName(request.getName());
        review.setStart_time(Timestamp.valueOf(request.getStart_time()));
        review.setEnd_time(Timestamp.valueOf(request.getEnd_time()));
        review = reviewRepository.saveAndFlush(review);
        int id = review.getId();
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("id", id);

        return jsonResponse;
    }

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
