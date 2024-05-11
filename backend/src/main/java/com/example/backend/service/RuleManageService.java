package com.example.backend.service;

import com.example.backend.dao.request.RuleDetailAddRequest;
import com.example.backend.dao.request.RuleTypeAddRequest;
import com.example.backend.dao.response.RuleDetailListResponse;
import com.example.backend.dao.response.RuleTypeListResponse;
import com.example.backend.entities.RuleType;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RuleManageService {
    @Transactional
    List<RuleType> getAllRuleType();
    @Transactional
    Optional<RuleType> getRuleTypeById(Integer id);
    @Transactional
    ResponseEntity<String>  addRuleType(RuleTypeAddRequest request);
    @Transactional
    ResponseEntity<String>  deleteRuleTypeById(Integer id);
    @Transactional
    ResponseEntity<String>  deleteRuleTypeByName(String name);
    @Transactional
    ResponseEntity<String>  updateRuleTypeById(RuleTypeAddRequest request);
    @Transactional
    ResponseEntity<String>  updateRuleTypeByName(String newtype, String oldtype);

    //ruleDetail
    @Transactional
    Page<Map<String,Object>> getAllRuleDetail(int page, int size);
    @Transactional
    ResponseEntity<String>  addRuleDetail(RuleDetailAddRequest request);
    @Transactional
    ResponseEntity<String>  updateRuleDetailById(RuleDetailAddRequest request);
    @Transactional
    ResponseEntity<String>  deleteRuleDetailById(Integer id);
    @Transactional
    float getRuleScore(String rule_type,String rule_detail);
    @Transactional
    Page<Map<String,Object>> getRuleDetailByCondition(String type,String detail,int page, int size);


}