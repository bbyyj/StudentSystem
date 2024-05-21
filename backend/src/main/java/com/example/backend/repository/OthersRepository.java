package com.example.backend.repository;

import com.example.backend.entities.Monograph;
import com.example.backend.entities.Others;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OthersRepository extends JpaRepository<Others, Integer> {
    @Modifying
    @Query(value = "select o.*" +
            "from others o join student s on o.sid = s.sid " +
            "where s.sid = :sid", nativeQuery = true)
    List<Others> getOthersBySid(@Param("sid") String sid);
}
