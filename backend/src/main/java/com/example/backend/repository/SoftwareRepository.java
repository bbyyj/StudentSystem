package com.example.backend.repository;

import com.example.backend.entities.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Integer> {
    @Modifying
    @Query(value = "select ss.*, s.name " +
            "from software ss " +
            "join student s on ss.sid = s.sid",
            nativeQuery = true)
    List<Map<String, Object>> getAll();

    @Modifying
    @Query(value = "select ss.*, s.name " +
            "from software ss " +
            "join student s on ss.sid = s.sid " +
            "where s.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getSoftwareByStudent(@Param("name") String name);

    @Modifying
    @Query(value = "select ss.*, s.name " +
            "from software ss join student s on ss.sid = s.sid " +
            "where ss.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getSoftwareByName(@Param("name") String name);


    @Modifying
    @Query(value = "select ss.*, s.name " +
            "from software ss " +
            "join student s on ss.sid = s.sid where s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassAll(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate);

    @Modifying
    @Query(value = "select ss.*, s.name " +
            "from software ss " +
            "join student s on ss.sid = s.sid " +
            "where s.name = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassSoftwareByStudent(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "select ss.*, s.name " +
            "from software ss join student s on ss.sid = s.sid " +
            "where ss.name = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassSoftwareByName(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "update software set check_status = :status, check_msg = :msg " +
            "where id = :id", nativeQuery = true)
    void updateCheckingStatus(@Param("id") int id, @Param("status") String status, @Param("msg") String msg);

    @Modifying
    @Query(value = "select sf.*, s.name " +
            "from software sf " +
            "join student s on sf.sid = s.sid " +
            "where s.sid = :sid and sf.check_status = 1 and sf.rule_accept = 1 " +
            "and sf.status_date >= :begin and sf.status_date <= :end", nativeQuery = true)
    List<Map<String, Object>> getStuSoftwareFromTime(@Param("sid") String sid,
                                                  @Param("begin") Date begin,
                                                  @Param("end") Date end);

    @Modifying
    @Query(value = "update software set check_score = :check_score " +
            "where id = :id", nativeQuery = true)
    void updateCheckScoreById(@Param("id") int id, @Param("check_score") double check_score);


    @Modifying
    @Query(value = "update software set rule_accept = :rule_accept " +
            "where id = :id", nativeQuery = true)
    void updateRuleAcceptById(@Param("id") int id, @Param("rule_accept") int rule_accept);
}
