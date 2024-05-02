package com.example.backend.controller;

import com.example.backend.dao.request.CompCheckRequest;
import com.example.backend.dao.request.CompetitionListRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> check(@RequestBody CompCheckRequest request){
        return examineService.check(request);
    }

    @PutMapping("/competition/del")
    public ResponseEntity<String> del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.del(id);
    }
}
