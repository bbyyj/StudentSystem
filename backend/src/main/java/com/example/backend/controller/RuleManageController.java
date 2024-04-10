package com.example.backend.controller;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.RuleTypeAddRequest;
import com.example.backend.dao.response.RuleDetailAllResponse;
import com.example.backend.dao.response.RuleDetailListResponse;
import com.example.backend.dao.response.RuleTypeListResponse;
import com.example.backend.entities.RuleType;
import com.example.backend.service.RuleManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rule")
public class RuleManageController {
    private final RuleManageService  ruleManageService;
    @GetMapping("/typeManage/getAllRuleType")
    public RuleTypeListResponse  getAllRuleType(){
        List<RuleType> ruleTypeList = ruleManageService.getAllRuleType();
        return new RuleTypeListResponse(ruleTypeList,ruleTypeList.size());
    }

    @PostMapping("/typeManage/addRuleType")
    public ResponseEntity<String> addRuleType(@RequestBody RuleTypeAddRequest request){
        return ruleManageService.addRuleType(request);
    }

    @DeleteMapping("/typeManage/deleteRuleTypeById")
    public ResponseEntity<String>  deleteRuleTypeById(@RequestParam int tid){
        return ruleManageService.deleteRuleTypeById(tid);
    }
    @DeleteMapping("/typeManage/deleteRuleTypeByName")
    public ResponseEntity<String>  deleteRuleTypeByName(@RequestParam String typename){
        return ruleManageService.deleteRuleTypeByName(typename);
    }
    @PutMapping("/typeManage/updateRuleTypeById")
    public ResponseEntity<String>  updateRuleTypeById(@RequestBody RuleTypeAddRequest request){
        System.out.println(request);
        return ruleManageService.updateRuleTypeById(request);
    }

    @GetMapping("/detailManage/getAllRuleDetail")
    public RuleDetailAllResponse  getAllRuleDetail(){
        List<RuleDetailListResponse>  ruleDetailList = ruleManageService.getAllRuleDetail();
        return new RuleDetailAllResponse(ruleDetailList);
    }



}