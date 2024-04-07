package com.example.backend.service.impl;

import com.example.backend.dao.request.RuleTypeAddRequest;
import com.example.backend.entities.RuleType;
import com.example.backend.repository.RuleTypeRepository;
import com.example.backend.service.RuleManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleManageServiceImpl implements RuleManageService {
    private final RuleTypeRepository  ruleTypeRepository ;
    
    
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
    public ResponseEntity<String>  deleteRuleTypeById(Integer id){

        try {
            ruleTypeRepository.deleteById(id);
            return ResponseEntity.ok("RuleType deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting RuleType by id.");
        }
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
            System.out.println(request.getId());
            System.out.println(request.getTypename());
            ruleTypeRepository.updateTypeById(request.getTypename(),request.getId());
            return ResponseEntity.ok("RuleType updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating RuleType by id.");
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
}
