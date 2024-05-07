package com.example.backend.repository;

import com.example.backend.entities.ExchangeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ExchangeActivityRepository extends JpaRepository<ExchangeActivity, Integer> {
    @Modifying
    @Query(value = "select a.*, s.name " +
            "from exchange_activity a " +
            "join student s on a.sid = s.sid",
            nativeQuery = true)
    List<Map<String, Object>> getAll();

    @Modifying
    @Query(value = "select a.*, s.name " +
            "from exchange_activity a " +
            "join student s on a.sid = s.sid " +
            "where s.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getExchangeByStudent(@Param("name") String name);

    @Modifying
    @Query(value = "select a.*, s.name " +
            "from exchange_activity a join student s on a.sid = s.sid " +
            "where a.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getExchangeByName(@Param("name") String name);


    @Modifying
    @Query(value = "select a.*, s.name " +
            "from exchange_activity a " +
            "join student s on a.sid = s.sid where s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassAll(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate);

    @Modifying
    @Query(value = "select a.*, s.name " +
            "from exchange_activity a " +
            "join student s on a.sid = s.sid " +
            "where s.name = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassExchangeByStudent(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "select a.*, s.name " +
            "from exchange_activity a join student s on a.sid = s.sid " +
            "where a.name = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassExchangeByName(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "update exchange_activity set check_status = :status, check_msg = :msg " +
            "where id = :id", nativeQuery = true)
    void updateCheckingStatus(@Param("id") int id, @Param("status") String status, @Param("msg") String msg);

    @Modifying
    @Query(value = "select a.* " +
            "from exchange_activity a " +
            "where a.sid = :sid and a.check_status ='已审核'" +
            "and a.begin >= :begin and a.begin <= :end", nativeQuery = true)
    List<Map<String, Object>> getStuActivityFromTime(@Param("sid") String sid,
                                                 @Param("begin") Date begin,
                                                 @Param("end") Date end);

    @Modifying
    @Query(value = "update exchange_activity set check_score = :check_score " +
            "where id = :id", nativeQuery = true)
    void updateCheckScoreById(@Param("id") int id, @Param("check_score") double check_score);


    @Modifying
    @Query(value = "update exchange_activity set rule_accept = :rule_accept " +
            "where id = :id", nativeQuery = true)
    void updateRuleAcceptById(@Param("id") int id, @Param("rule_accept") int rule_accept);
}
