package com.example.backend.repository;

import com.example.backend.entities.RuleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleTypeRepository extends JpaRepository<RuleType, Integer> {
    List<RuleType> findAll();
    Optional<RuleType> findById(Integer id);
    void deleteById(Integer id);
    @Modifying
    @Query("delete from RuleType where type = ?1")
    void deleteByType(String type);
    @Modifying
    @Query("update RuleType as r set r.type = :type where r.tid = :id")
    void updateTypeById(String type, Integer id);
    @Modifying
    @Query("update RuleType set type = ?1 where type = ?2")
    void updateTypeByName(String newtype, String oldtype);

}
