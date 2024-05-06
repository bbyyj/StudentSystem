package com.example.backend.repository;

import com.example.backend.entities.SocialWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialWorkRepository extends JpaRepository<SocialWork, Integer> {

}
