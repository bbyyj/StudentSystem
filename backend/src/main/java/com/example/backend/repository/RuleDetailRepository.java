package com.example.backend.repository;

import com.example.backend.entities.RuleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RuleDetailRepository extends JpaRepository<RuleDetail, Integer> {
    @Query(value="select rd.rid,rt.type,rd.detail,rd.score from rule_detail rd join rule_type rt on  rd.tid = rt.tid ", nativeQuery = true)
    Page<Map<String, Object>> getAll(Pageable pageable);
    @Query(value="select rd.rid,rt.type,rd.detail,rd.score from rule_detail rd join rule_type rt on  rd.tid = rt.tid where rt.type = ?1 and rd.detail like %?2% ", nativeQuery = true)
    Page<Map<String, Object>> getRuleDetailByCondition(String type,String detail,Pageable pageable);

    @Query("select rd from RuleDetail rd where rd.tid = ?1")
    List<RuleDetail> findAllByTid(Integer tid);

    @Modifying
    @Query("update RuleDetail set score = ?2,title = ?3 where rid = ?1 ")
    void updateByRid(Integer rid,float score,String detail);
    @Modifying
    @Query("delete from RuleDetail where rid = ?1")
    void deleteByRid(Integer rid);

    @Query("select rd.score from RuleDetail rd where rd.tid=?1 and rd.title =?2")
    float findScoreByTidAndTitle(Integer tid,String title);


}