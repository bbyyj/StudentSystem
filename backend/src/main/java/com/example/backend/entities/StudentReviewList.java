package com.example.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_review_list")
public class StudentReviewList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rid")
    private int review_id;
    @Column(name = "sid")
    private String student_id;
    @Column(name = "sname")
    private String student_name;
    @Column(name = "is_undergraduate")
    private boolean isUndergraduate;
    @Column(name = "admission_year")
    private Year admission_year;
    @Column(name = "class_id")
    private Integer classId;
    @Column(name = "state")
    private String state;
    @Column(name = "score")
    private float score;
    @Column(name = "exam_score")
    private float exam_score;
    @Column(name = "final_score")
    private float final_score;
}
