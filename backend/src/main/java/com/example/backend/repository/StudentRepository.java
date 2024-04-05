package com.example.backend.repository;

import com.example.backend.entities.Student;
import com.example.backend.entities.StudentRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByNetId(String netId);

    @Modifying
    @Query("UPDATE Student s SET s.dormitory = :dormitory WHERE s.netId = :netId")
    void updateDormitoryByNetId(String netId, String dormitory);

    List<Student> findAll();

    List<Student> findByIsUndergraduateAndAdmissionYearAndClassId(Boolean isUndergraduate, Year admissionYear, Integer classId);

    @Modifying
    @Query("UPDATE Student s SET s.email = :email, s.address = :address, s.phone = :phone, s.urgentPhone = :urgentPhone, s.urgentName = :urgentName, s.wechat = :wechat WHERE s.netId = :netId")
    void updateMyInfo(String netId, String email, String address, String phone, String urgentPhone, String urgentName, String wechat);

    @Modifying
    @Query("UPDATE Student s SET s.studentRole = :studentRole, s.isUndergraduate = :isUndergraduate, s.admissionYear = :admissionYear, " +
            "s.classId = :classId, s.name = :name, s.type = :type, s.nation = :nation,  s.sid = :sid, s.pid = :pid, " +
            "s.birth = :birth, s.politics = :politics, s.nativePlace = :nativePlace, s.dormitory = :dormitory WHERE s.netId = :netId")
    void updateStuInfo(String netId,StudentRole studentRole, boolean isUndergraduate,Year admissionYear,Integer classId,
                       String name, String type,String nation,String sid,String pid,String birth, String politics, String nativePlace, String dormitory);

    @Modifying
    @Query("UPDATE Student s SET s.password = :encode WHERE s.netId = :netId")
    void updatePasswordByNetId(String netId, String encode);

    void deleteByNetId(String netId);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:keyword% OR s.sid LIKE %:keyword%")
    List<Student> findByKeyword(String keyword);

}
