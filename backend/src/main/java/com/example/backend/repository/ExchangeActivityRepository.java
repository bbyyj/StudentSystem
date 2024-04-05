package com.example.backend.repository;

import com.example.backend.entities.ExchangeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeActivityRepository extends JpaRepository<ExchangeActivity, Integer> {

}
