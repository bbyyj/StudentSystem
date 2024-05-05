package com.example.backend.controller;

import com.example.backend.dao.request.CompCheckRequest;
import com.example.backend.dao.request.CompetitionListRequest;
import com.example.backend.dao.request.PaperCheckRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.dao.response.PaperListResponse;
import com.example.backend.dao.response.PatentListResponse;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/examine")
public class ExamineController {
    private final ExamineService examineService;

    /*
    api for Competition
     */
    @PostMapping("/loadingdata/competition")
    public CompetitionListResponse getCompetitionList(@RequestBody CompetitionListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean comp_name = Objects.equals(select, "比赛名称") && !Objects.equals(search, "");
        boolean type = Objects.equals(select, "成果级别") && !Objects.equals(search, "");
        boolean level = Objects.equals(select, "成果等级") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllCompetitionList();
            } else if (stu_name) {
                return examineService.getAllCompetitionByStudentName(search);
            } else if (comp_name) {
                return examineService.getAllCompetitionByCompName(search);
            } else if (type) {
                return examineService.getAllCompetitionByType(search);
            } else if (level) {
                return examineService.getAllCompetitionByLevel(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                // 查询某个班的所有比赛
                return examineService.getClassCompetitionList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassCompetitionByStudentName(classId, year, isUndergraduate, search);
            } else if (comp_name) {
                return examineService.getClassCompetitionByCompName(classId, year, isUndergraduate, search);
            } else if (type) {
                return examineService.getClassCompetitionByType(classId, year, isUndergraduate, search);
            } else if (level) {
                return examineService.getClassCompetitionByLevel(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/competition/check")
    public ResponseEntity<String> comp_check(@RequestBody CompCheckRequest request){
        return examineService.comp_check(request);
    }

    @PutMapping("/competition/del")
    public ResponseEntity<String> comp_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.comp_del(id);
    }


     /*
     api for Paper
     */
    @PostMapping("/loadingdata/paper")
    public PaperListResponse getPaperList(@RequestBody CompetitionListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean paper_name = Objects.equals(select, "论文名称") && !Objects.equals(search, "");
        boolean type = Objects.equals(select, "期刊/会议") && !Objects.equals(search, "");
        boolean CCF = Objects.equals(select, "CCF等级") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllPaperList();
            } else if (stu_name) {
                return examineService.getAllPaperByStudentName(search);
            } else if (paper_name) {
                return examineService.getAllPaperByPaperName(search);
            } else if (type) {
                return examineService.getAllPaperByType(search);
            } else if (CCF) {
                return examineService.getAllPaperByCCF(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                return examineService.getClassPaperList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassPaperByStudentName(classId, year, isUndergraduate, search);
            } else if (paper_name) {
                return examineService.getClassPaperByPaperName(classId, year, isUndergraduate, search);
            } else if (type) {
                return examineService.getClassPaperByType(classId, year, isUndergraduate, search);
            } else if (CCF) {
                return examineService.getClassPaperByCCF(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/paper/check")
    public ResponseEntity<String> paper_check(@RequestBody PaperCheckRequest request){
        return examineService.paper_check(request);
    }

    @PutMapping("/paper/del")
    public ResponseEntity<String> paper_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.paper_del(id);
    }


    /*
     api for Patent
     */
    @PostMapping("/loadingdata/patent")
    public PatentListResponse getPatentList(@RequestBody CompetitionListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean patent_name = Objects.equals(select, "专利名称") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllPatentList();
            } else if (stu_name) {
                return examineService.getAllPatentByStudentName(search);
            } else if (patent_name) {
                return examineService.getAllPatentByPatentName(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                return examineService.getClassPatentList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassPatentByStudentName(classId, year, isUndergraduate, search);
            } else if (patent_name) {
                return examineService.getClassPatentByPaperName(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/patent/check")
    public ResponseEntity<String> patent_check(@RequestBody PaperCheckRequest request){
        return examineService.patent_check(request);
    }

    @PutMapping("/patent/del")
    public ResponseEntity<String> patent_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.patent_del(id);
    }
}
