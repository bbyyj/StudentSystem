package com.example.backend.repository;

import com.example.backend.entities.Review;
import com.example.backend.entities.RuleType;
import com.example.backend.entities.StudentReviewList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentReviewListRepository extends JpaRepository<StudentReviewList, Integer> {
    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 and r.isUndergraduate=?2 and r.admission_year=?3 and r.classId=?4 ")
    List<StudentReviewList> findByReviewId(int review_id, boolean isUndergraduate, Year admission_year, Integer classId);

}
