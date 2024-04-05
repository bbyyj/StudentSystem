package com.example.backend.repository;

import com.example.backend.entities.Monograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonographRepository extends JpaRepository<Monograph, Integer> {

}
