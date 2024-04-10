package com.example.backend.dao.response;

import com.example.backend.entities.RuleDetail;
import com.example.backend.entities.RuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDetailListResponse {
    private String title;
    private int tid;
    private int level=1;
    private List<RuleDetail> RuleDetailList;
//    private int count;
}
