package com.example.backend.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDetailAllResponse {
//    private int count;
    private List<RuleDetailListResponse> treeData;

}
