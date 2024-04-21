package com.example.backend.service.impl;

import com.example.backend.dao.request.CompCheckRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.repository.CompetitionRepository;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public CompetitionListResponse getAllCompetitionByStudentName(String name){
        List<Map<String, Object>> lc = repository.getCompetitionByStudent(name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByCompName(String name) {
        List<Map<String, Object>> lc = repository.getCompetitionByName(name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByType(String type) // 成果级别 (国际级/...)
    {
        List<Map<String, Object>> lc = repository.getCompetitionByType(type);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByLevel(String level) // 成果等级
    {
        List<Map<String, Object>> lc = repository.getCompetitionByLevel(level);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = repository.getClassAll(classId, year, isUndergraduate);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = repository.getClassCompetitionByStudent(classId, year, isUndergraduate, name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByCompName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = repository.getClassCompetitionByName(classId, year, isUndergraduate, name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByType(String classId, String year, Boolean isUndergraduate, String type) {
        List<Map<String, Object>> lc = repository.getClassCompetitionByType(classId, year, isUndergraduate, type);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByLevel(String classId, String year, Boolean isUndergraduate, String level) {
        List<Map<String, Object>> lc = repository.getClassCompetitionByLevel(classId, year, isUndergraduate, level);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> check(CompCheckRequest request) {
        try {
            repository.updateCheckingStatus(
                    request.getId(),
                    request.getStatus(),
                    request.getMsg()
            );
            return ResponseEntity.ok("check successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while checking.");
        }
    }

    @Override
    public ResponseEntity<String> del(int id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting competition.");
        }
    }
}
