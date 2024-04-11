package com.example.backend.dao.response;

import com.example.backend.entities.Competition;
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
public class CompetitionListResponse {
    private List<Competition> CompetitionList;
    private int count;
}
