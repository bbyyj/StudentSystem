package com.example.backend.service.impl;

import com.example.backend.dao.request.CompetitionListRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.entities.Competition;
import com.example.backend.repository.CompetitionRepository;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamineServiceImpl implements ExamineService {
    private final CompetitionRepository repository;
    @Override
    public CompetitionListResponse getAllCompetitionList() {
        List<Competition> lc = repository.getAll();
        return new CompetitionListResponse(lc, lc.size());
    }


}
