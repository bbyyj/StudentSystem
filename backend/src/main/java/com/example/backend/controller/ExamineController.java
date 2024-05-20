package com.example.backend.controller;

import com.example.backend.dao.request.CheckRequest;
import com.example.backend.dao.request.ListRequest;
import com.example.backend.dao.response.ListResponse;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
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
    public ListResponse getCompetitionList(@RequestBody ListRequest request){
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
    public ResponseEntity<String> comp_check(@RequestBody CheckRequest request){
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
    public ListResponse getPaperList(@RequestBody ListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean paper_name = Objects.equals(select, "论文名称") && !Objects.equals(search, "");
        boolean type = Objects.equals(select, "论文类型") && !Objects.equals(search, "");
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
    public ResponseEntity<String> paper_check(@RequestBody CheckRequest request){
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
    public ListResponse getPatentList(@RequestBody ListRequest request){
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
                return examineService.getClassPatentByPatentName(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/patent/check")
    public ResponseEntity<String> patent_check(@RequestBody CheckRequest request){
        return examineService.patent_check(request);
    }

    @PutMapping("/patent/del")
    public ResponseEntity<String> patent_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.patent_del(id);
    }

    /*
    api for Software
    */
    @PostMapping("/loadingdata/software")
    public ListResponse getSoftwareList(@RequestBody ListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean software_name = Objects.equals(select, "软著名称") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllSoftwareList();
            } else if (stu_name) {
                return examineService.getAllSoftwareByStudentName(search);
            } else if (software_name) {
                return examineService.getAllSoftwareBySoftwareName(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                return examineService.getClassSoftwareList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassSoftwareByStudentName(classId, year, isUndergraduate, search);
            } else if (software_name) {
                return examineService.getClassSoftwareBySoftwareName(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/software/check")
    public ResponseEntity<String> software_check(@RequestBody CheckRequest request){
        return examineService.software_check(request);
    }

    @PutMapping("/software/del")
    public ResponseEntity<String> software_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.software_del(id);
    }


    /*
    api for Monograph
    */
    @PostMapping("/loadingdata/monograph")
    public ListResponse getMonographList(@RequestBody ListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean monograph_name = Objects.equals(select, "专著名称") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllMonographList();
            } else if (stu_name) {
                return examineService.getAllMonographByStudentName(search);
            } else if (monograph_name) {
                return examineService.getAllMonographByMonographName(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                return examineService.getClassMonographList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassMonographByStudentName(classId, year, isUndergraduate, search);
            } else if (monograph_name) {
                return examineService.getClassMonographByMonographName(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/monograph/check")
    public ResponseEntity<String> monograph_check(@RequestBody CheckRequest request){
        return examineService.monograph_check(request);
    }

    @PutMapping("/monograph/del")
    public ResponseEntity<String> monograph_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.monograph_del(id);
    }


    /*
    api for ExchangeActivity
    */
    @PostMapping("/loadingdata/exchange")
    public ListResponse getExchangeList(@RequestBody ListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");
        boolean activity = Objects.equals(select, "项目名称") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllExchangeList();
            } else if (stu_name) {
                return examineService.getAllExchangeByStudentName(search);
            } else if (activity) {
                return examineService.getAllExchangeByActivityName(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                return examineService.getClassExchangeList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassExchangeByStudentName(classId, year, isUndergraduate, search);
            } else if (activity) {
                return examineService.getClassExchangeByActivityName(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/exchange/check")
    public ResponseEntity<String> exchange_check(@RequestBody CheckRequest request){
        return examineService.activity_check(request);
    }

    @PutMapping("/exchange/del")
    public ResponseEntity<String> exchange_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.activity_del(id);
    }


    /*
    api for Volunteer
    */
    @PostMapping("/loadingdata/volunteer")
    public ListResponse getVolunteerList(@RequestBody ListRequest request){
        String search = request.getSearch();
        String select = request.getSelect();
        String classId = request.getClassId();
        String year = request.getYear();
        Boolean isUndergraduate = request.getIsUndergraduate();

        boolean none = Objects.equals(search, "") && Objects.equals(select, "");
        boolean stu_name = Objects.equals(select, "学生姓名") && !Objects.equals(search, "");


        if(Objects.equals(classId, "") && Objects.equals(year, "")){
            if(none){
                return examineService.getAllVolunteerList();
            } else if (stu_name) {
                return examineService.getAllVolunteerByStudentName(search);
            }
        }else if(!Objects.equals(classId, "") && !Objects.equals(year, "")){
            if(none){
                return examineService.getClassVolunteerList(classId, year, isUndergraduate);
            } else if (stu_name) {
                return examineService.getClassVolunteerByStudentName(classId, year, isUndergraduate, search);
            }
        }else{
            return null;
        }

        return null;
    }

    @PostMapping("/volunteer/check")
    public ResponseEntity<String> volunteer_time_check(@RequestBody CheckRequest request){
        return examineService.volunteer_time_check(request);
    }

    @PutMapping("/volunteer/del")
    public ResponseEntity<String> volunteer_time_del(@RequestBody Map<String, Object> request){
        int id = (int)request.get("id");
        return examineService.volunteer_time_del(id);
    }
}
