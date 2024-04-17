package com.example.backend.repository;

import com.example.backend.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

//    @Modifying
//    @Query(value = "", nativeQuery = true)
//    List<Competition> getCompetitionByName();
//
//    @Modifying
//    @Query(value = "", nativeQuery = true)
//    List<Competition> getCompetitionByLevel();
//
//    @Modifying
//    @Query(value = "", nativeQuery = true)
//    List<Competition> getCompetitionByDegree();
}
