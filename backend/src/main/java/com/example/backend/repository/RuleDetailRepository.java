package com.example.backend.repository;

import com.example.backend.entities.RuleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleDetailRepository extends JpaRepository<RuleDetail, Integer> {
    @Query("select rd from RuleDetail rd where rd.tid = ?1")
    List<RuleDetail> findAllByTid(Integer tid);

//    @Query("select new com.example.backend.entities.RuleDetail(rd.rid, rd.tid, rd.score, rd.detail) from RuleDetail rd where rd.tid = ?1")
//    List<RuleDetail> findAllByTid(Integer tid);

    @Modifying
    @Query("update RuleDetail set score = ?2,title = ?3 where rid = ?1 ")
    void updateByRid(Integer rid,float score,String detail);
    @Modifying
    @Query("delete from RuleDetail where rid = ?1")
    void deleteByRid(Integer rid);

    @Query("select rd.score from RuleDetail rd where rd.tid=?1 and rd.title =?2")
    float findScoreByTidAndTitle(Integer tid,String title);


}