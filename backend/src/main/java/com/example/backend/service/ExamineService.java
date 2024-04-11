package com.example.backend.service;

import com.example.backend.dao.request.CompetitionListRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import org.springframework.stereotype.Service;

@Service
public interface ExamineService {
    CompetitionListResponse getAllCompetitionList();
}
