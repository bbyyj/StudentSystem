package com.example.backend.repository;

import com.example.backend.entities.Review;
import com.example.backend.entities.RuleType;
import com.example.backend.entities.StudentReviewList;
import com.example.backend.entities.StudentReviewListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface StudentReviewListRepository extends JpaRepository<StudentReviewList, Integer> {
    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 and r.isUndergraduate=?2 and r.admission_year=?3 and r.classId=?4 ")
    Page<StudentReviewList> findByReviewId(int review_id, boolean isUndergraduate, Year admission_year, Integer classId, Pageable  pageable);
    @Modifying
    @Query("UPDATE StudentReviewList r SET r.state='已审核' ,r.score=?1 where r.review_id=?2 and r.student_id=?3")
    void updateScoreAndState(double score, int review_id, String student_id);
    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 and r.isUndergraduate=?2 and r.admission_year=?3 and r.classId=?4  and r.student_id like %?6% and r.student_name like %?7% and r.state like %?5%  ")
    Page<StudentReviewList> findByReviewIdAndCondition(int review_id, boolean isUndergraduate, Year admission_year, Integer classId, String state,String sid,String sname,Pageable  pageable);
    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 ")
    Page<StudentReviewList> findAll(int review_id, Pageable  pageable);
    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 and r.isUndergraduate=?2 and r.admission_year=?3  ")
    Page<StudentReviewList> findByYear(int review_id, boolean isUndergraduate, Year admission_year,  Pageable  pageable);
    @Modifying
    @Query("UPDATE StudentReviewList r SET r.exam_score=?1,r.final_score=?2 where r.review_id=?3 and r.student_id=?4")
    void updateExamScore(double exam_score,double final_score, int review_id, String student_id);
    @Query("SELECT r FROM StudentReviewList r where r.review_id=?1 and r.student_id=?2")
    Optional<StudentReviewList> findByReviewIdAndStudentId(int review_id, String student_id);
//@Query("SELECT new com.example.backend.entities.StudentReviewListDTO(" +
//        "r.id, r.review_id, r.student_id,r.student_name," +
//        "CASE WHEN r.isUndergraduate = true THEN '本科生' ELSE '研究生' END ,r.admission_year,r.classId,r.state,r.score,r.exam_score,r.final_score) "+
//        "FROM StudentReviewList r " +
//        "WHERE r.review_id = ?1")
//Page<StudentReviewListDTO> findAll(int review_id, Pageable pageable);

}
