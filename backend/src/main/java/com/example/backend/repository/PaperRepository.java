package com.example.backend.repository;

import com.example.backend.entities.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {
    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p " +
            "join student s on p.sid = s.sid",
            nativeQuery = true)
    List<Map<String, Object>> getAll();

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p " +
            "join student s on p.sid = s.sid " +
            "where s.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getPaperByStudent(@Param("name") String name);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p join student s on p.sid = s.sid where p.title = :name", nativeQuery = true)
    List<Map<String, Object>> getPaperByName(@Param("name") String name);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p join student s on p.sid = s.sid where p.paper_type = :type", nativeQuery = true)
    List<Map<String, Object>> getPaperByType(@Param("type") String type);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p join student s on p.sid = s.sid where p.ccf_level = :level", nativeQuery = true)
    List<Map<String, Object>> getPaperByLevel(@Param("level") String level);


    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p " +
            "join student s on p.sid = s.sid where s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassAll(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p " +
            "join student s on p.sid = s.sid " +
            "where s.name = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassPaperByStudent(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p join student s on p.sid = s.sid " +
            "where p.title = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassPaperByName(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p join student s on p.sid = s.sid " +
            "where p.paper_type = :result_type and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassPaperByType(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("result_type") String result_type);

    @Modifying
    @Query(value = "select p.*, s.name as student_name " +
            "from paper p join student s on p.sid = s.sid " +
            "where p.ccf_level = :level and s.class_id = :classId and s.admission_year = :year and is_undergraduate = :isUndergraduate", nativeQuery = true)
    List<Map<String, Object>> getClassPaperByLevel(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("level") String level);

    @Modifying
    @Query(value = "update paper set check_status = :status, check_msg = :msg " +
            "where id = :id", nativeQuery = true)
    void updateCheckingStatus(@Param("id") int id, @Param("status") String status, @Param("msg") String msg);

    @Modifying
    @Query(value = "select p.* " +
            "from paper p " +
            "where p.sid = :sid and p.check_status = '已审核' " +
            "and p.acc_date >= :begin and p.acc_date <= :end", nativeQuery = true)
    List<Map<String, Object>> getStuPaperFromTime(@Param("sid") String sid,
                                                 @Param("begin") Date begin,
                                                 @Param("end") Date end);

    @Modifying
    @Query(value = "update paper set check_score = :check_score " +
            "where id = :id", nativeQuery = true)
    void updateCheckScoreById(@Param("id") int id, @Param("check_score") double check_score);


    @Modifying
    @Query(value = "update paper set rule_accept = :rule_accept " +
            "where id = :id", nativeQuery = true)
    void updateRuleAcceptById(@Param("id") int id, @Param("rule_accept") int rule_accept);
}
