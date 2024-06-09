package com.example.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.dao.request.StudentReviewListRequest;
import com.example.backend.entities.Review;
import com.example.backend.entities.StudentReviewList;
//import com.example.backend.entities.StudentReviewListDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.Year;
import java.util.List;
import java.util.Map;

public interface RuleReviewService {
    @Transactional
    JSONObject addReview(ReviewAddRequest request);
    @Transactional
    ResponseEntity<String> updateReview(ReviewAddRequest request);
    @Transactional
    Page<Review> getReviews(int page, int size);
    @Transactional
    Page<Review> getReviewsByCondition(String name,int page, int size);
    @Transactional
    Page<StudentReviewList> getStudentReviewList(StudentReviewListRequest request,int page, int size);
    @Transactional
    Page<StudentReviewList> getStudentReviewListByCondition(StudentReviewListRequest request,int page, int size,
                                                            String state,String sid,String sname);
    @Transactional
    Page<Map<String, Object>>  getStudentMatiarial (int review_id, String student_id,int page,int size);
    @Transactional
    ResponseEntity<String> submitReview(List<Map<String, Object>> data);
    @Transactional
    Page<StudentReviewList> getAllStudentReviewList(int id, int page, int size);
    @Transactional
    Page<StudentReviewList> getStudentReviewListByTeacher(int id, boolean isUndergraduate, Year admissionYear,int page, int size);
    @Transactional
    ResponseEntity<String> upLoadExamScore(int id,MultipartFile file);




}