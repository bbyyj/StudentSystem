package com.example.backend.service;

import com.example.backend.dao.request.RuleTypeAddRequest;
import com.example.backend.dao.response.RuleDetailListResponse;
import com.example.backend.dao.response.RuleTypeListResponse;
import com.example.backend.entities.RuleType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    List<RuleDetailListResponse> getAllRuleDetail();

}
