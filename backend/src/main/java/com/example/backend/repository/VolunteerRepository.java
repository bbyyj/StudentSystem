package com.example.backend.repository;

import com.example.backend.entities.Software;
import com.example.backend.entities.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    @Modifying
    @Query(value = "select v.*, s.name as student_name " +
            "from volunteer v " +
            "join student s on v.sid = s.sid",
            nativeQuery = true)
    List<Map<String, Object>> getAll();

    @Modifying
    @Query(value = "select v.*, s.name as student_name " +
            "from volunteer v " +
            "join student s on v.sid = s.sid " +
            "where s.name = :name",
            nativeQuery = true)
    List<Map<String, Object>> getVolunteerByStudent(@Param("name") String name);



    @Modifying
    @Query(value = "select v.*, s.name as student_name " +
            "from volunteer v " +
            "join student s on v.sid = s.sid where s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassAll(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate);

    @Modifying
    @Query(value = "select v.*, s.name as student_name " +
            "from volunteer v " +
            "join student s on v.sid = s.sid " +
            "where s.name = :name and s.class_id = :classId and s.admission_year = :year and s.is_undergraduate = :isUndergraduate",
            nativeQuery = true)
    List<Map<String, Object>> getClassVolunteerByStudent(@Param("classId") String classId, @Param("year") String year, @Param("isUndergraduate") Boolean isUndergraduate, @Param("name") String name);


    @Modifying
    @Query(value = "update volunteer set check_status = :status, check_msg = :msg " +
            "where id = :id", nativeQuery = true)
    void updateCheckingStatus(@Param("id") int id, @Param("status") String status, @Param("msg") String msg);

    @Modifying
    @Query(value = "select v.* " +
            "from volunteer v " +
            "where v.sid = :sid and v.check_status = '已审核' " +
            "and v.begin >= :begin and v.end <= :end", nativeQuery = true)
    List<Map<String, Object>> getStuVolunteerFromTime(@Param("sid") String sid,
                                                     @Param("begin") Date begin,
                                                     @Param("end") Date end);

    @Modifying
    @Query(value = "update volunteer set check_score = :check_score " +
            "where id = :id", nativeQuery = true)
    void updateCheckScoreById(@Param("id") int id, @Param("check_score") double check_score);


    @Modifying
    @Query(value = "update volunteer set rule_accept = :rule_accept " +
            "where id = :id", nativeQuery = true)
    void updateRuleAcceptById(@Param("id") int id, @Param("rule_accept") int rule_accept);

    @Modifying
    @Query(value = "select v.*" +
            "from volunteer v join student s on v.sid = s.sid " +
            "where s.sid = :sid", nativeQuery = true)
    List<Volunteer> getVolunteerBySid(@Param("sid") String sid);
}
