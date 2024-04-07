package com.example.backend.dao.response;

import com.example.backend.entities.RuleType;
import com.example.backend.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleTypeListResponse {
    private List<RuleType> RuleTypeList;
    private int count;
}
