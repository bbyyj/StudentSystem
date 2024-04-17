package com.example.backend.controller;

import com.example.backend.dao.request.CompetitionListRequest;
import com.example.backend.dao.response.CompetitionListResponse;
import com.example.backend.service.ExamineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if(Objects.equals(search, "") && Objects.equals(select, "")){
            return examineService.getAllCompetitionList();
        } else if (Objects.equals(select, "学生姓名") && !Objects.equals(search, "")) {
            return examineService.getCompetitionByStudentName(search);
        }
        return null;
    }


}
