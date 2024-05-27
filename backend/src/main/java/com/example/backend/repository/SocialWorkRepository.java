package com.example.backend.repository;

import com.example.backend.entities.Patent;
import com.example.backend.entities.SocialWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialWorkRepository extends JpaRepository<SocialWork, Integer> {
    @Modifying
    @Query(value = "select sw.*" +
            "from social_work sw join student s on sw.sid = s.sid " +
            "where s.sid = :sid", nativeQuery = true)
    List<SocialWork> getSocialWorkBySid(@Param("sid") String sid);
}
