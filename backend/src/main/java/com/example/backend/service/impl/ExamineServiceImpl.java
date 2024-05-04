package com.example.backend.service.impl;

import com.example.backend.dao.request.CompCheckRequest;
import com.example.backend.dao.request.PaperCheckRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.dao.response.PaperListResponse;
import com.example.backend.repository.CompetitionRepository;
import com.example.backend.repository.PaperRepository;
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
    private final CompetitionRepository comp;
    private final PaperRepository paper;

    @Override
    public CompetitionListResponse getAllCompetitionList() {
        List<Map<String, Object>> lc = comp.getAll();
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByStudentName(String name){
        List<Map<String, Object>> lc = comp.getCompetitionByStudent(name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByCompName(String name) {
        List<Map<String, Object>> lc = comp.getCompetitionByName(name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByType(String type) // 成果级别 (国际级/...)
    {
        List<Map<String, Object>> lc = comp.getCompetitionByType(type);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getAllCompetitionByLevel(String level) // 成果等级
    {
        List<Map<String, Object>> lc = comp.getCompetitionByLevel(level);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = comp.getClassAll(classId, year, isUndergraduate);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByStudent(classId, year, isUndergraduate, name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByCompName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByName(classId, year, isUndergraduate, name);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByType(String classId, String year, Boolean isUndergraduate, String type) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByType(classId, year, isUndergraduate, type);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public CompetitionListResponse getClassCompetitionByLevel(String classId, String year, Boolean isUndergraduate, String level) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByLevel(classId, year, isUndergraduate, level);
        return new CompetitionListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> comp_check(CompCheckRequest request) {
        try {
            comp.updateCheckingStatus(
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
    public ResponseEntity<String> comp_del(int id) {
        try {
            comp.deleteById(id);
            return ResponseEntity.ok("delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting competition.");
        }
    }

    /*
     * operation about Paper
     */

    @Override
    public PaperListResponse getAllPaperList() {
        List<Map<String, Object>> lc = paper.getAll();
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getAllPaperByStudentName(String name) {
        List<Map<String, Object>> lc = paper.getPaperByStudent(name);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getAllPaperByPaperName(String name) {
        List<Map<String, Object>> lc = paper.getPaperByName(name);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getAllPaperByType(String type) {
        List<Map<String, Object>> lc = paper.getPaperByType(type);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getAllPaperByCCF(String level) {
        List<Map<String, Object>> lc = paper.getPaperByLevel(level);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getClassPaperList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = paper.getClassAll(classId, year, isUndergraduate);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getClassPaperByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = paper.getClassPaperByStudent(classId, year, isUndergraduate, name);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getClassPaperByPaperName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = paper.getClassPaperByName(classId, year, isUndergraduate, name);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getClassPaperByType(String classId, String year, Boolean isUndergraduate, String type) {
        List<Map<String, Object>> lc = paper.getClassPaperByType(classId, year, isUndergraduate, type);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public PaperListResponse getClassPaperByCCF(String classId, String year, Boolean isUndergraduate, String level) {
        List<Map<String, Object>> lc = paper.getClassPaperByLevel(classId, year, isUndergraduate, level);
        return new PaperListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> paper_check(PaperCheckRequest request) {
        try {
            paper.updateCheckingStatus(
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
    public ResponseEntity<String> paper_del(int id) {
        try {
            paper.deleteById(id);
            return ResponseEntity.ok("delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting competition.");
        }
    }
}
