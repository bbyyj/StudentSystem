package com.example.backend.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionListRequest {
    private int pageNum;
    private int pageSize;
    private String select;
    private String search;
    private String classId;
    private String year;
    private Boolean isUndergraduate;
}


