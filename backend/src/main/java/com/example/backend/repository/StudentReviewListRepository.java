package com.example.backend.repository;

import com.example.backend.entities.Review;
import com.example.backend.entities.RuleType;
import com.example.backend.entities.StudentReviewList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<StudentReviewList> findByReviewId(int review_id, boolean isUndergraduate, Year admission_year, Integer classId, Pageable  pageable);
    @Modifying
    @Query("UPDATE StudentReviewList r SET r.state=1 ,r.score=?1 where r.review_id=?2 and r.student_id=?3")
    void updateScoreAndState(double score, int review_id, String student_id);
//    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 and r.isUndergraduate=?2 and r.admission_year=?3 and r.classId=?4  and r.student_id=?5 and r.sname=?6 ")
//    Page<StudentReviewList> findByReviewIdAndCondition(int review_id, boolean isUndergraduate, Year admission_year, Integer classId, String sid,String sname,Pageable  pageable);

}
