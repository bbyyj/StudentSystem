package com.example.backend.service.impl;

import com.example.backend.dao.request.RuleDetailAddRequest;
import com.example.backend.dao.request.RuleTypeAddRequest;
import com.example.backend.dao.response.RuleDetailListResponse;
import com.example.backend.dao.response.RuleTypeListResponse;
import com.example.backend.entities.RuleDetail;
import com.example.backend.entities.RuleType;
import com.example.backend.repository.RuleDetailRepository;
import com.example.backend.repository.RuleTypeRepository;
import com.example.backend.service.RuleManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleManageServiceImpl implements RuleManageService {
    private final RuleTypeRepository  ruleTypeRepository ;
    private final RuleDetailRepository  ruleDetailRepository ;
    
    
    public List<RuleType> getAllRuleType(){
        return ruleTypeRepository.findAll();
    }
    
    public Optional<RuleType> getRuleTypeById(Integer id){
        return ruleTypeRepository.findById(id);
    }
    public ResponseEntity<String> addRuleType(RuleTypeAddRequest request){

        try {
            RuleType ruleType = new RuleType();
            ruleType.setType(request.getTypename());
            ruleTypeRepository.save(ruleType);
            return ResponseEntity.ok("RuleType added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding RuleType.");
        }
    }
    public ResponseEntity<String> addRuleDetail(RuleDetailAddRequest request){

        try {
            RuleDetail ruleDetail = new RuleDetail();
            ruleDetail.setTitle(request.getDetail());
            ruleDetail.setScore(request.getScore());
            ruleDetail.setTid(request.getTid());
            ruleDetailRepository.save(ruleDetail);
            return ResponseEntity.ok("RuleDetail added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding RuleDetail.");
        }
    }
    public ResponseEntity<String>  deleteRuleTypeById(Integer id){

        try {
            ruleTypeRepository.deleteById(id);
            return ResponseEntity.ok("RuleType deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting RuleType by id.");
        }
    }
    public ResponseEntity<String>  deleteRuleDetailById(Integer id){

        try {
            ruleDetailRepository.deleteById(id);
            return ResponseEntity.ok("RuleDetail deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting RuleDetail by id.");
        }
    }

    @Override
    public float getRuleScore(String rule_type, String rule_detail) {
        int  ruleTypeId = ruleTypeRepository.findIdByType(rule_type);
        float score = ruleDetailRepository.findScoreByTidAndTitle(ruleTypeId,rule_detail);
        return score;
    }

    public ResponseEntity<String> deleteRuleTypeByName(String name){

        try {
            ruleTypeRepository.deleteByType(name);
            return ResponseEntity.ok("RuleType deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting RuleType by name.");
        }
    }
    public ResponseEntity<String>  updateRuleTypeById(RuleTypeAddRequest request){

        try {

            ruleTypeRepository.updateTypeById(request.getTypename(),request.getId());
            return ResponseEntity.ok("RuleType updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating RuleType by id.");
        }
    }
    public ResponseEntity<String>  updateRuleDetailById(RuleDetailAddRequest request){

        try {

            ruleDetailRepository.updateByRid(request.getId(),request.getScore(),request.getDetail());
            return ResponseEntity.ok("RuleDetail updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating RuleDetail by id.");
        }
    }
    public ResponseEntity<String>  updateRuleTypeByName(String newtype, String oldtype){

        try {
            ruleTypeRepository.updateTypeByName(newtype,oldtype);
            return ResponseEntity.ok("RuleType updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating RuleType by name.");
        }
    }
    public Page<Map<String,Object>> getAllRuleDetail(int page, int size){
        Pageable pageable= PageRequest.of(page, size);
        return ruleDetailRepository.getAll(pageable);
    }
    public Page<Map<String,Object>> getRuleDetailByCondition(String type,String detail,int page, int size){
        Pageable pageable= PageRequest.of(page, size);
        return ruleDetailRepository.getRuleDetailByCondition(type,detail,pageable);
    }
}
