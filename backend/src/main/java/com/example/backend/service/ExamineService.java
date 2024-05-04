package com.example.backend.service;

import com.example.backend.dao.request.CompCheckRequest;
import com.example.backend.dao.request.PaperCheckRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.dao.response.PaperListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ExamineService {
    CompetitionListResponse getAllCompetitionList();

    CompetitionListResponse getAllCompetitionByStudentName(String name);

    CompetitionListResponse getAllCompetitionByCompName(String name);

    CompetitionListResponse getAllCompetitionByType(String type); // 成果类别 (学科竞赛/艺术比赛/体育比赛/其他)

    CompetitionListResponse getAllCompetitionByLevel(String level); // 成果等级

    // 针对某个班
    CompetitionListResponse getClassCompetitionList(String classId, String year, Boolean isUndergraduate);

    CompetitionListResponse getClassCompetitionByStudentName(String classId, String year, Boolean isUndergraduate, String name);

    CompetitionListResponse getClassCompetitionByCompName(String classId, String year, Boolean isUndergraduate, String name);

    CompetitionListResponse getClassCompetitionByType(String classId, String year, Boolean isUndergraduate, String type); // 成果类别 (学科竞赛/艺术比赛/体育比赛/其他)

    CompetitionListResponse getClassCompetitionByLevel(String classId, String year, Boolean isUndergraduate, String level); // 成果等级

    @Transactional
    ResponseEntity<String> comp_check(CompCheckRequest request);

    @Transactional
    ResponseEntity<String> comp_del(int id);

    PaperListResponse getAllPaperList();

    PaperListResponse getAllPaperByStudentName(String search);

    PaperListResponse getAllPaperByPaperName(String search);

    PaperListResponse getAllPaperByType(String search);

    PaperListResponse getAllPaperByCCF(String search);

    PaperListResponse getClassPaperList(String classId, String year, Boolean isUndergraduate);

    PaperListResponse getClassPaperByStudentName(String classId, String year, Boolean isUndergraduate, String search);

    PaperListResponse getClassPaperByPaperName(String classId, String year, Boolean isUndergraduate, String search);

    PaperListResponse getClassPaperByType(String classId, String year, Boolean isUndergraduate, String search);

    PaperListResponse getClassPaperByCCF(String classId, String year, Boolean isUndergraduate, String search);

    @Transactional
    ResponseEntity<String> paper_check(PaperCheckRequest request);

    @Transactional
    ResponseEntity<String> paper_del(int id);
}
