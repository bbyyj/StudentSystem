package com.example.backend.service.impl;

import com.example.backend.dao.request.CheckRequest;
import com.example.backend.dao.response.ListResponse;
import com.example.backend.repository.CompetitionRepository;
import com.example.backend.repository.PaperRepository;
import com.example.backend.repository.PatentRepository;
import com.example.backend.repository.SoftwareRepository;
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
    private final PatentRepository patent;
    private final SoftwareRepository software;


    /*
     * operation about Competition
     */
    @Override
    public ListResponse getAllCompetitionList() {
        List<Map<String, Object>> lc = comp.getAll();
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllCompetitionByStudentName(String name){
        List<Map<String, Object>> lc = comp.getCompetitionByStudent(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllCompetitionByCompName(String name) {
        List<Map<String, Object>> lc = comp.getCompetitionByName(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllCompetitionByType(String type) // 成果级别 (国际级/...)
    {
        List<Map<String, Object>> lc = comp.getCompetitionByType(type);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllCompetitionByLevel(String level) // 成果等级
    {
        List<Map<String, Object>> lc = comp.getCompetitionByLevel(level);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassCompetitionList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = comp.getClassAll(classId, year, isUndergraduate);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassCompetitionByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByStudent(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassCompetitionByCompName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByName(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassCompetitionByType(String classId, String year, Boolean isUndergraduate, String type) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByType(classId, year, isUndergraduate, type);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassCompetitionByLevel(String classId, String year, Boolean isUndergraduate, String level) {
        List<Map<String, Object>> lc = comp.getClassCompetitionByLevel(classId, year, isUndergraduate, level);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> comp_check(CheckRequest request) {
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
    public ListResponse getAllPaperList() {
        List<Map<String, Object>> lc = paper.getAll();
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllPaperByStudentName(String name) {
        List<Map<String, Object>> lc = paper.getPaperByStudent(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllPaperByPaperName(String name) {
        List<Map<String, Object>> lc = paper.getPaperByName(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllPaperByType(String type) {
        List<Map<String, Object>> lc = paper.getPaperByType(type);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllPaperByCCF(String level) {
        List<Map<String, Object>> lc = paper.getPaperByLevel(level);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPaperList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = paper.getClassAll(classId, year, isUndergraduate);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPaperByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = paper.getClassPaperByStudent(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPaperByPaperName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = paper.getClassPaperByName(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPaperByType(String classId, String year, Boolean isUndergraduate, String type) {
        List<Map<String, Object>> lc = paper.getClassPaperByType(classId, year, isUndergraduate, type);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPaperByCCF(String classId, String year, Boolean isUndergraduate, String level) {
        List<Map<String, Object>> lc = paper.getClassPaperByLevel(classId, year, isUndergraduate, level);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> paper_check(CheckRequest request) {
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


    /*
     * operation for Patent
     */
    @Override
    public ListResponse getAllPatentList() {
        List<Map<String, Object>> lc = patent.getAll();
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllPatentByStudentName(String name) {
        List<Map<String, Object>> lc = patent.getPatentByStudent(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllPatentByPatentName(String name) {
        List<Map<String, Object>> lc = patent.getPatentByName(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPatentList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = patent.getClassAll(classId, year, isUndergraduate);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPatentByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = patent.getClassPatentByStudent(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassPatentByPatentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = patent.getClassPatentByName(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> patent_check(CheckRequest request) {
        try {
            patent.updateCheckingStatus(
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
    public ResponseEntity<String> patent_del(int id) {
        try {
            patent.deleteById(id);
            return ResponseEntity.ok("delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting competition.");
        }
    }

    /*
     operations for Software
     */
    @Override
    public ListResponse getAllSoftwareList() {
        List<Map<String, Object>> lc = software.getAll();
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllSoftwareByStudentName(String name) {
        List<Map<String, Object>> lc = software.getSoftwareByStudent(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getAllSoftwareBySoftwareName(String name) {
        List<Map<String, Object>> lc = software.getSoftwareByName(name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassSoftwareList(String classId, String year, Boolean isUndergraduate) {
        List<Map<String, Object>> lc = software.getClassAll(classId, year, isUndergraduate);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassSoftwareByStudentName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = software.getClassSoftwareByStudent(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ListResponse getClassSoftwareBySoftwareName(String classId, String year, Boolean isUndergraduate, String name) {
        List<Map<String, Object>> lc = software.getClassSoftwareByName(classId, year, isUndergraduate, name);
        return new ListResponse(lc, lc.size());
    }

    @Override
    public ResponseEntity<String> software_check(CheckRequest request) {
        try {
            software.updateCheckingStatus(
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
    public ResponseEntity<String> software_del(int id) {
        try {
            software.deleteById(id);
            return ResponseEntity.ok("delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting competition.");
        }
    }


}
