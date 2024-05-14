package com.example.backend.repository;

import com.example.backend.entities.Review;
import com.example.backend.entities.RuleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r")
    Page<Review> getAll(Pageable pageable);
    @Query("SELECT r FROM Review r WHERE r.name LIKE %?1%")
    Page<Review> getByCondition(String name,Pageable pageable);
    @Query("SELECT r FROM Review r WHERE r.id = ?1")
    Review findById(int id);
    @Modifying
    @Query("UPDATE Review r SET r.name=?2,r.start_time=?3,r.end_time=?4  WHERE r.id = ?1")
    void update(int id, String name, Date start_time, Date end_time);

}
