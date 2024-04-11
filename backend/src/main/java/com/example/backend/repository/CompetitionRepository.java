package com.example.backend.repository;

import com.example.backend.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
    @Modifying
    @Query("select c from Competition c")
    List<Competition> getAll();

    @Modifying
    @Query(value = "", nativeQuery = true)
    List<Competition> getCompetitionByStudent();

    @Modifying
    @Query(value = "", nativeQuery = true)
    List<Competition> getCompetitionByName();

    @Modifying
    @Query(value = "", nativeQuery = true)
    List<Competition> getCompetitionByLevel();

    @Modifying
    @Query(value = "", nativeQuery = true)
    List<Competition> getCompetitionByDegree();
}
