package com.example.backend.service;

import com.example.backend.dao.request.CheckRequest;
import com.example.backend.dao.response.ListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ExamineService {
    ListResponse getAllCompetitionList();

    ListResponse getAllCompetitionByStudentName(String name);

    ListResponse getAllCompetitionByCompName(String name);

    ListResponse getAllCompetitionByType(String type); // 成果类别 (学科竞赛/艺术比赛/体育比赛/其他)

    ListResponse getAllCompetitionByLevel(String level); // 成果等级

    // 针对某个班
    ListResponse getClassCompetitionList(String classId, String year, Boolean isUndergraduate);

    ListResponse getClassCompetitionByStudentName(String classId, String year, Boolean isUndergraduate, String name);

    ListResponse getClassCompetitionByCompName(String classId, String year, Boolean isUndergraduate, String name);

    ListResponse getClassCompetitionByType(String classId, String year, Boolean isUndergraduate, String type); // 成果类别 (学科竞赛/艺术比赛/体育比赛/其他)

    ListResponse getClassCompetitionByLevel(String classId, String year, Boolean isUndergraduate, String level); // 成果等级

    @Transactional
    ResponseEntity<String> comp_check(CheckRequest request);

    @Transactional
    ResponseEntity<String> comp_del(int id);

    ListResponse getAllPaperList();

    ListResponse getAllPaperByStudentName(String search);

    ListResponse getAllPaperByPaperName(String search);

    ListResponse getAllPaperByType(String search);

    ListResponse getAllPaperByCCF(String search);

    ListResponse getClassPaperList(String classId, String year, Boolean isUndergraduate);

    ListResponse getClassPaperByStudentName(String classId, String year, Boolean isUndergraduate, String search);

    ListResponse getClassPaperByPaperName(String classId, String year, Boolean isUndergraduate, String search);

    ListResponse getClassPaperByType(String classId, String year, Boolean isUndergraduate, String search);

    ListResponse getClassPaperByCCF(String classId, String year, Boolean isUndergraduate, String search);

    @Transactional
    ResponseEntity<String> paper_check(CheckRequest request);

    @Transactional
    ResponseEntity<String> paper_del(int id);

    ListResponse getAllPatentList();

    ListResponse getAllPatentByStudentName(String search);

    ListResponse getAllPatentByPatentName(String search);

    ListResponse getClassPatentList(String classId, String year, Boolean isUndergraduate);

    ListResponse getClassPatentByStudentName(String classId, String year, Boolean isUndergraduate, String search);

    ListResponse getClassPatentByPatentName(String classId, String year, Boolean isUndergraduate, String search);

    @Transactional
    ResponseEntity<String> patent_check(CheckRequest request);

    @Transactional
    ResponseEntity<String> patent_del(int id);

    ListResponse getAllSoftwareList();

    ListResponse getAllSoftwareByStudentName(String search);

    ListResponse getAllSoftwareBySoftwareName(String search);

    ListResponse getClassSoftwareList(String classId, String year, Boolean isUndergraduate);

    ListResponse getClassSoftwareByStudentName(String classId, String year, Boolean isUndergraduate, String search);

    ListResponse getClassSoftwareBySoftwareName(String classId, String year, Boolean isUndergraduate, String search);

    @Transactional
    ResponseEntity<String> software_check(CheckRequest request);

    @Transactional
    ResponseEntity<String> software_del(int id);
}
