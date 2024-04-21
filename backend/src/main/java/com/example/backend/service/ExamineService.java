package com.example.backend.service;

import com.example.backend.dao.request.CompCheckRequest;
import com.example.backend.dao.response.CompetitionListResponse;
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
    ResponseEntity<String> check(CompCheckRequest request);

    @Transactional
    ResponseEntity<String> del(int id);

}
