package com.example.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.dao.request.StudentReviewListRequest;
import com.example.backend.entities.Review;
import com.example.backend.entities.Student;
import com.example.backend.entities.StudentReviewList;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.StudentReviewListRepository;
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
    private final StudentReviewListRepository  studentReviewListRepository;
    private final StudentRepository  studentRepository;
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

    @Override
    public List<StudentReviewList> getStudentReviewList(StudentReviewListRequest request) {
        List<StudentReviewList> studentReviewLists = studentReviewListRepository.findByReviewId(request.getId(),request.getIsUndergraduate(),request.getAdmission_year(),request.getClassId());
        if(studentReviewLists.isEmpty()){
            List<Student>  students = studentRepository.findByIsUndergraduateAndAdmissionYearAndClassId(request.getIsUndergraduate(), request.getAdmission_year(), request.getClassId());
            for(Student student:students){
                StudentReviewList studentReviewList = new StudentReviewList();
                studentReviewList.setReview_id(request.getId());
                studentReviewList.setStudent_id(student.getNetId());
                studentReviewList.setStudent_name(student.getName());
                studentReviewList.setUndergraduate(student.isUndergraduate());
                studentReviewList.setAdmission_year(student.getAdmissionYear());
                studentReviewList.setClassId(student.getClassId());
                studentReviewListRepository.saveAndFlush(studentReviewList);
            }
            studentReviewLists = studentReviewListRepository.findByReviewId(request.getId(),request.getIsUndergraduate(),request.getAdmission_year(),request.getClassId());

        }
        return studentReviewLists;
    }
}
