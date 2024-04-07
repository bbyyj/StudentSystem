package com.example.backend.controller;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.RuleTypeAddRequest;
import com.example.backend.dao.response.RuleTypeListResponse;
import com.example.backend.entities.RuleType;
import com.example.backend.service.RuleManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rule/typeManage")
public class RuleManageController {
    private final RuleManageService  ruleManageService;
    @GetMapping("/getAllRuleType")
    public RuleTypeListResponse  getAllRuleType(){
        List<RuleType> ruleTypeList = ruleManageService.getAllRuleType();
        return new RuleTypeListResponse(ruleTypeList,ruleTypeList.size());
    }

    @PostMapping("/addRuleType")
    public ResponseEntity<String> addRuleType(@RequestBody RuleTypeAddRequest request){
        return ruleManageService.addRuleType(request);
    }

    @DeleteMapping("/deleteRuleTypeById")
    public ResponseEntity<String>  deleteRuleTypeById(@RequestParam int tid){
        return ruleManageService.deleteRuleTypeById(tid);
    }
    @DeleteMapping("/deleteRuleTypeByName")
    public ResponseEntity<String>  deleteRuleTypeByName(@RequestParam String typename){
        return ruleManageService.deleteRuleTypeByName(typename);
    }
    @PutMapping("/updateRuleTypeById")
    public ResponseEntity<String>  updateRuleTypeById(@RequestBody RuleTypeAddRequest request){
        System.out.println(request);
        return ruleManageService.updateRuleTypeById(request);
    }
}
