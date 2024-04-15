package com.example.backend.service.impl;

import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.repository.CompetitionRepository;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExamineServiceImpl implements ExamineService {
    private final CompetitionRepository repository;
    @Override
    public CompetitionListResponse getAllCompetitionList() {
        List<Map<String, Object>> lc = repository.getAll();
        return new CompetitionListResponse(lc, lc.size());
    }


}
