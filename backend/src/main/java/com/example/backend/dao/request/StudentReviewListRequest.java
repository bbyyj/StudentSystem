package com.example.backend.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentReviewListRequest {
    private Integer id;
    private Boolean isUndergraduate;
    private Year admission_year;
    private Integer classId;
}
