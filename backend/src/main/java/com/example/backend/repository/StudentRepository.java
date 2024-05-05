package com.example.backend.repository;

import com.example.backend.entities.Student;
import com.example.backend.entities.StudentRole;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByNetId(String netId);

    @Query("SELECT s FROM Student s")
    Page<Student> findByPage(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.dormitory = :dormitory WHERE s.sid = :sid")
    void updateDormitoryBySid(String sid, String dormitory);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.name = :name WHERE s.sid = :sid")
    void updateNameBySid(String sid, String name);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.type = :type WHERE s.sid = :sid")
    void updateTypeBySid(String sid, String type);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.nation = :nation WHERE s.sid = :sid")
    void updateNationBySid(String sid, String nation);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.pid = :pid WHERE s.sid = :sid")
    void updatePidBySid(String sid, String pid);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.birth = :birth WHERE s.sid = :sid")
    void updateBirthBySid(String sid, String birth);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.politics = :politics WHERE s.sid = :sid")
    void updatePoliticsBySid(String sid, String politics);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.nativePlace = :nativePlace WHERE s.sid = :sid")
    void updateNativePlaceBySid(String sid, String nativePlace);

    List<Student> findAll();

    Page<Student> findByIsUndergraduateAndAdmissionYearAndClassId(Boolean isUndergraduate, Year admissionYear, Integer classId, Pageable pageable);
    List<Student> findByIsUndergraduateAndAdmissionYearAndClassId(Boolean isUndergraduate, Year admissionYear, Integer classId);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.email = :email, s.address = :address, s.phone = :phone, s.urgentPhone = :urgentPhone, s.urgentName = :urgentName, s.wechat = :wechat WHERE s.netId = :netId")
    void updateMyInfo(String netId, String email, String address, String phone, String urgentPhone, String urgentName, String wechat);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.studentRole = :studentRole, s.isUndergraduate = :isUndergraduate, s.admissionYear = :admissionYear, " +
            "s.classId = :classId, s.name = :name, s.type = :type, s.nation = :nation,  s.sid = :sid, s.pid = :pid, " +
            "s.birth = :birth, s.politics = :politics, s.nativePlace = :nativePlace, s.dormitory = :dormitory WHERE s.netId = :netId")
    void updateStuInfo(String netId,StudentRole studentRole, boolean isUndergraduate,Year admissionYear,Integer classId,
                       String name, String type,String nation,String sid,String pid,String birth, String politics, String nativePlace, String dormitory);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.password = :encode WHERE s.netId = :netId")
    void updatePasswordByNetId(String netId, String encode);

    @Transactional
    void deleteByNetId(String netId);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:keyword% OR s.sid LIKE %:keyword%")
    Page<Student> findByKeyword(String keyword, Pageable pageable);


}
