package com.example.backend.repository;

import com.example.backend.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c " +
            "join student s on c.sid = s.sid",
            nativeQuery = true)
    List<Map<String, Object>> getAll();

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c " +
            "join student s on c.sid = s.sid " +
            "where s.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getCompetitionByStudent(@Param("name") String name);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c join student s on c.sid = s.sid where c.competition_name = :name", nativeQuery = true)
    List<Map<String, Object>> getCompetitionByName(@Param("name") String name);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c join student s on c.sid = s.sid where c.result_type = :result_type", nativeQuery = true)
    List<Map<String, Object>> getCompetitionByType(@Param("result_type") String result_type);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c join student s on c.sid = s.sid where c.result_level = :result_level", nativeQuery = true)
    List<Map<String, Object>> getCompetitionByLevel(@Param("result_level") String result_level);


    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c " +
            "join student s on c.sid = s.sid where s.class_id = :classId and s.admission_year = :year and is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassAll(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c " +
            "join student s on c.sid = s.sid " +
            "where s.name = :name and s.class_id = :classId and s.admission_year = :year and is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassCompetitionByStudent(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c join student s on c.sid = s.sid " +
            "where c.competition_name = :name and s.class_id = :classId and s.admission_year = :year and is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassCompetitionByName(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c join student s on c.sid = s.sid " +
            "where c.result_type = :result_type and s.class_id = :classId and s.admission_year = :year and is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassCompetitionByType(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("result_type") String result_type);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c join student s on c.sid = s.sid " +
            "where c.result_level = :result_level and s.class_id = :classId and s.admission_year = :year and is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassCompetitionByLevel(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("result_level") String result_level);

    @Modifying
    @Query(value = "update competition set check_status = :status, check_msg = :msg " +
            "where id = :id", nativeQuery = true)
    void updateCheckingStatus(@Param("id") int id, @Param("status") String status, @Param("msg") String msg);

    @Modifying
    @Query(value = "select c.*, s.name " +
            "from competition c " +
            "join student s on c.sid = s.sid " +
            "where s.sid = :sid and c.check_status = 1 and c.rule_accept = 1 " +
            "and c.time >= :begin and c.time <= :end", nativeQuery = true)
    List<Map<String, Object>> getStuCompFromTime(@Param("sid") String sid,
                                                 @Param("begin") Date begin,
                                                 @Param("end") Date end);

    @Modifying
    @Query(value = "update competition set check_score = :check_score " +
            "where id = :id", nativeQuery = true)
    void updateCheckScoreById(@Param("id") int id, @Param("check_score") float check_score);


    @Modifying
    @Query(value = "update competition set rule_accept = :rule_accept " +
            "where id = :id", nativeQuery = true)
    void updateRuleAcceptById(@Param("id") int id, @Param("rule_accept") int rule_accept);
}
